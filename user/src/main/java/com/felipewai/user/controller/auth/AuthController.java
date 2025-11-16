package com.felipewai.user.controller.auth;

import com.felipewai.user.dto.user.LoginRequestDTO;
import com.felipewai.user.dto.user.RegisterUserRequestDTO;
import com.felipewai.user.dto.user.UserResponse;
import com.felipewai.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO){
        return ResponseEntity.ok(authService.register(registerUserRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

}
