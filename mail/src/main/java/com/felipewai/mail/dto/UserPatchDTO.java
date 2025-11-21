package com.felipewai.mail.dto;

public record UserPatchDTO(
        Long id,

        String name,

        String surname,

        String email,

        String phone,

        String description
) {
}
