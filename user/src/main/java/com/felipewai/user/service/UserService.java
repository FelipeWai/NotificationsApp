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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (requestDTO.name() != null) {
            user.setName(requestDTO.name());
        }

        if (requestDTO.surname() != null) {
            user.setSurname(requestDTO.surname());
        }

        if (requestDTO.email() != null) {
            user.setEmail(requestDTO.email());
        }

        if (requestDTO.phone() != null) {
            user.setPhone(requestDTO.phone());
        }

        if (requestDTO.description() != null) {
            user.setDescription(requestDTO.description());
        }

        eventPublisher.publish("user.updated", userMapper.toResponse(user));
        return userRepository.save(user);
    }

}
