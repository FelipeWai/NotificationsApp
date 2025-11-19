package com.felipewai.user.dto.user;

import jakarta.validation.constraints.Email;

import java.util.Optional;

public record PatchUserRequestDTO(
        Optional<String> name,

        Optional<String> surname,

        Optional<String> email,

        Optional<String> phone,

        Optional<String> description
) {
}
