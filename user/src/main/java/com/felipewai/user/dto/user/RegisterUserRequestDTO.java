package com.felipewai.user.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        String surname,

        @NotBlank(message = "Phone cannot be blank")
        String phone,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email is not valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
