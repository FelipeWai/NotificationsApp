package com.felipewai.user.controller;

import com.felipewai.user.dto.user.PatchUserRequestDTO;
import com.felipewai.user.dto.user.UserResponse;
import com.felipewai.user.mapper.UserMapper;
import com.felipewai.user.model.User;
import com.felipewai.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchUser(
            @PathVariable Long id,
            @Valid @RequestBody PatchUserRequestDTO requestDTO,
            @AuthenticationPrincipal User authenticatedUser){

        if (!authenticatedUser.getId().equals(id)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User savedUser = userService.patchUser(id, requestDTO);
        return ResponseEntity.ok(userMapper.toResponse(savedUser));
    }

}
