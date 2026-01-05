package com.buddyNetwork.BuddyNetwork.dto;

import com.buddyNetwork.BuddyNetwork.model.User;

public record UserAuthResponseDTO(

                Long id,
                String username,
                String email,
                String fullname,
                String token) {
        public UserAuthResponseDTO(User user, String token) {
                this(user.getId(), user.getUsername(), user.getEmail(), user.getFullname(), token);
        }
}
