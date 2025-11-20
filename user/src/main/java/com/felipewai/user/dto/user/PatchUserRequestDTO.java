package com.felipewai.user.dto.user;

public record PatchUserRequestDTO(
        String name,

        String surname,

        String email,

        String phone,

        String description
) {
}
