package com.felipewai.user.dto.user;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,
        String phone
) {
}
