package com.felipewai.user.service;

import com.felipewai.user.dto.user.PatchUserRequestDTO;
import com.felipewai.user.mapper.UserMapper;
import com.felipewai.user.model.User;
import com.felipewai.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.felipewai.user.config.RabbitMQConfig.USER_EXCHANGE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;
    private final UserMapper userMapper;

    @Transactional
    public User patchUser(Long id, PatchUserRequestDTO requestDTO){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String loggedEmail;

        if (principal instanceof User user) {
            loggedEmail = user.getEmail();
        } else if (principal instanceof UserDetails userDetails) {
            loggedEmail = userDetails.getUsername();
        } else {
            throw new RuntimeException("Unexpected principal type");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(loggedEmail)) {
            throw new RuntimeException("You cannot update another user");
        }

        applyPatch(requestDTO, user);
        eventPublisher.publish("user.updated", userMapper.toResponse(user));
        return userRepository.save(user);
    }

    private void applyPatch(PatchUserRequestDTO dto, User user) {

        dto.name().ifPresent(user::setName);
        dto.surname().ifPresent(user::setSurname);
        dto.email().ifPresent(user::setEmail);
        dto.phone().ifPresent(user::setPhone);
        dto.description().ifPresent(user::setDescription);

    }

}
