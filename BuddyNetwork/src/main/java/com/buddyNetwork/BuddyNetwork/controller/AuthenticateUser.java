package com.buddyNetwork.BuddyNetwork.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddyNetwork.BuddyNetwork.dto.UserAuthLoginRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthLoginResponseDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthResponseDTO;
import com.buddyNetwork.BuddyNetwork.service.UserService;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.buddyNetwork.BuddyNetwork.utility.ApiResponse;
import com.buddyNetwork.BuddyNetwork.utility.ResponseUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// Auth Controller to Signup and login User and ...
@Slf4j
@RestController
@RequestMapping("/api/auth/v1")
public class AuthenticateUser {

    // @Autowired
    // private AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticateUser(UserService userService) {
        this.userService = userService;
    }

    // HTTP POST: /auth/signup request (Create new User Account)
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserAuthResponseDTO>> signupUser(
            @Valid @RequestBody UserAuthRequestDTO request) {
        // Call UserService to handle signup logic (to be implemented)
        log.info("User Registration Start");
        UserAuthResponseDTO userAuth = userService.createUser(request);

        log.info("User Register Successfully!");

        return ResponseEntity.ok(ResponseUtil.success(userAuth, "User Register Successfully", "/"));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthLoginResponseDTO> loginUser(@Valid @RequestBody UserAuthLoginRequestDTO req) {
        log.info("User Login Start");
        log.info("User entity is created");
        UserAuthLoginResponseDTO userAuth = userService.authenticateUser(req);
        log.info("User logged in Successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuth);
    }
}
