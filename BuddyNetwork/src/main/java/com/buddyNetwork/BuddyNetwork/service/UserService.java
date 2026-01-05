package com.buddyNetwork.BuddyNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buddyNetwork.BuddyNetwork.dto.UserAuthLoginRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthLoginResponseDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthResponseDTO;
import com.buddyNetwork.BuddyNetwork.model.User;
import com.buddyNetwork.BuddyNetwork.repository.UserRepository;
import com.buddyNetwork.BuddyNetwork.utility.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtService;

    public UserService(UserRepository _userRepository, JwtUtil jwtSerUtil) {
        this.userRepository = _userRepository;
        this.jwtService = jwtSerUtil;
    }

    public UserAuthResponseDTO createUser(UserAuthRequestDTO reqUser) {
        log.info("User Signup Service start");

        if (reqUser.email() == null) {
            log.info("Invalid email");

            throw new IllegalArgumentException("Email is required");
        }

        if (reqUser.password() == null) {
            log.info("Invalid password");

            throw new IllegalArgumentException("Password is required");
        }

        if (reqUser.username() == null) {
            log.info("Invalid Username");

            throw new IllegalArgumentException("Username is required");
        }

        if (reqUser.fullname() == null) {
            log.info("Invalid Fullname");
            throw new IllegalArgumentException("Fullname is required");
        }

        User newUser = new User();
        newUser.setEmail(reqUser.email());
        newUser.setUsername(reqUser.username());
        newUser.setFullname(reqUser.fullname());
        newUser.setPassword(passwordEncoder.encode(reqUser.password()));
        newUser.setGender(reqUser.gender());
        log.info("User password encrypted");

        User savedUser = userRepository.save(newUser);
        log.info("User saved in database");

        String token = jwtService.generateToken(savedUser);
        log.info("Token generated");

        UserAuthResponseDTO response = new UserAuthResponseDTO(savedUser, token);

        log.info("User Signup response created");

        return response;

    }

    public UserAuthLoginResponseDTO authenticateUser(UserAuthLoginRequestDTO reqUser) {
        log.info("User Authentication Service start");
        User existingUser = userRepository.findByEmail(reqUser.email());

        if (existingUser == null) {
            log.info("Invalid email");
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (!passwordEncoder.matches(reqUser.password(), existingUser.getPassword())) {
            log.info("Invalid password");
            throw new IllegalArgumentException("Invalid email or password");
        }
        log.info("User found");

        String token = jwtService.generateToken(existingUser);
        log.info("Token is created");

        UserAuthLoginResponseDTO loginResponse = new UserAuthLoginResponseDTO(existingUser, token);
        log.info("User Login response is created");
        return loginResponse;
    }

    public User loadUserByUsername(String username) {
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }

        User user = userRepository.findByUsername(username);

        if (user.getFullname() == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        return user;
    }
}
