package com.buddyNetwork.BuddyNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buddyNetwork.BuddyNetwork.dto.UserAuthResponseDTO;
import com.buddyNetwork.BuddyNetwork.model.User;
import com.buddyNetwork.BuddyNetwork.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    public UserAuthResponseDTO createUser(User reqUser) {
        if (reqUser.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }

        if (reqUser.getPassword() == null) {
            throw new IllegalArgumentException("Password is required");
        }

        if (reqUser.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        }

        if (reqUser.getFullname() == null) {
            throw new IllegalArgumentException("Fullname is required");
        }

        reqUser.setPassword(passwordEncoder.encode(reqUser.getPassword()));

        User savedUser = userRepository.save(reqUser);

        UserAuthResponseDTO response = new UserAuthResponseDTO(savedUser.getId(), savedUser.getEmail(),
                savedUser.getFullname(), savedUser.getUsername());

        return response;

    }
}
