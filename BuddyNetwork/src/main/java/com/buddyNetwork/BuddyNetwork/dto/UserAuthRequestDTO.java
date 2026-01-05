package com.buddyNetwork.BuddyNetwork.dto;

import jakarta.validation.constraints.*;

public record UserAuthRequestDTO(

        @NotBlank(message = "Username is required") String username,

        @Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,

        @Size(min = 8, message = "Password must be at least 8 characters") @NotBlank(message = "Password is required") String password,

        String fullname,
        char gender

) {
}
