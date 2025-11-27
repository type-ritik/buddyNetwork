package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddyNetwork.BuddyNetwork.repository.UserRepo;

// Auth Controller to Signup and login User and ...
@RestController
@RequestMapping("/auth")
public class AuthenticateUser {
    private String fullName;
    private String emailAddress;
    private String password;

    // HTTP POST: /auth/signup request (Create new User Account)
    @PostMapping("/signup")
    public String signupUser(@RequestBody UserRepo userInfo) {
        // Set User Fullname()
        userInfo.setFullName();
        // Fetch User FullName
        this.fullName = userInfo.getFullName();
        // Fetch User Valid EmailAddress
        this.emailAddress = userInfo.getEmailAddress();
        // Fetch User Valid Password
        this.password = userInfo.getPassword();

        System.out.println("User signed up with email: " + this.emailAddress + " successfully!");

        System.out.println("User signed up with password: " + this.password + " successfully!");

        System.out.println("User signed up with name: " + this.fullName + " successfully!");

        // Return Response Account Creation is Successful
        return "User signed up with name: " + this.fullName;
    }
}
