package com.felipewai.user.service;

import com.felipewai.user.dto.user.LoginRequestDTO;
import com.felipewai.user.dto.user.RegisterUserRequestDTO;
import com.felipewai.user.dto.user.UserResponse;
import com.felipewai.user.mapper.UserMapper;
import com.felipewai.user.model.User;
import com.felipewai.user.repository.UserRepository;
import com.felipewai.user.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EventPublisher eventPublisher;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse register(RegisterUserRequestDTO requestDTO){
        if (userRepository.findByEmail(requestDTO.email()).isPresent()){
            throw new RuntimeException("Email already exists");
        }else if(userRepository.findByPhone(requestDTO.phone()).isPresent()){
            throw new RuntimeException("Phone already exists");
        }

        String passwordHashed = passwordEncoder.encode(requestDTO.password());
        User user = userMapper.toModel(requestDTO);
        user.setPasswordHash(passwordHashed);

        User savedUser = userRepository.save(user);
        eventPublisher.publish("user.created", userMapper.toResponse(savedUser));
        return userMapper.toResponse(savedUser);
    }

    public String login(LoginRequestDTO requestDTO){
        User user = userRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(requestDTO.password(), user.getPasswordHash())){
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getId(), user.getEmail());
    }

}
