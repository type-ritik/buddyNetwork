package com.buddyNetwork.BuddyNetwork.dto;

import com.buddyNetwork.BuddyNetwork.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthLoginResponseDTO(
        @Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
        @NotBlank(message = "Token error") String token,
        @NotBlank(message = "Full name") String fullname,
        @NotBlank(message = "User name") String username) {

    public UserAuthLoginResponseDTO(User user, String token) {
        this(user.getEmail(), token, user.getFullname(), user.getUsername());
    }

}
