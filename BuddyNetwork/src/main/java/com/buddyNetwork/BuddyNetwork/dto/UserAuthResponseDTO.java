package com.buddyNetwork.BuddyNetwork.dto;

public record UserAuthResponseDTO(

        Long id,
        String username,
        String email,
        String fullname) {
}
