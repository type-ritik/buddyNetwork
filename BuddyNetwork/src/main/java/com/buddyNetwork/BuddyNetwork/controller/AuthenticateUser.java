package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthResponseDTO;
import com.buddyNetwork.BuddyNetwork.model.User;
import com.buddyNetwork.BuddyNetwork.service.UserService;

import jakarta.validation.Valid;

// Auth Controller to Signup and login User and ...
@RestController
@RequestMapping("/auth")
public class AuthenticateUser {

    private final UserService userService;

    public AuthenticateUser(UserService userService) {
        this.userService = userService;
    }

    // HTTP POST: /auth/signup request (Create new User Account)
    @PostMapping("/user/signup")
    public ResponseEntity<UserAuthResponseDTO> signupUser(
            @Valid @RequestBody UserAuthRequestDTO request) {
        // Call UserService to handle signup logic (to be implemented)
        User userEntity = new User(request);
        UserAuthResponseDTO userAuth = userService.createUser(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userAuth);
    }
}
