package com.buddyNetwork.BuddyNetwork.repository;

import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

// User Model
@Repository
public class UserRepo {
    // User FullName
    private String fullName;
    // User FirstName
    private String firstName;
    // User LastName
    private String lastName;
    // User EmailAddress
    private String emailAddress;
    // User Password
    private String password;

    // Set User FirstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Set User LastName
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    // Set User FullName
    public void setFullName() {
        this.fullName = firstName + " " + lastName;
    }

    // Set User EmailAddress
    public void setEmailAddress(String emailAddress) {

        // Check if Email Address is valid
        if (!isValidEmail(emailAddress)) {
            System.out.println("Invalid email address format.");
            throw new IllegalArgumentException("Invalid email address format.");
        }
        this.emailAddress = emailAddress;
    }

    // Set User Password
    public void setPassword(String password) {

        // Check if Password is valid
        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long.");
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.password = password;
    }

    // Fetch FullName
    public String getFullName() {
        return this.fullName;
    }

    // Fetch EmailAddress
    public String getEmailAddress() {
        return this.emailAddress;
    }

    // Fetch Password
    public String getPassword() {
        return this.password;
    }

    // Method to Check password Validation
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    // Method to Check email Validation
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);

        return email != null && p.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "UserRequest{name='" + fullName + "'}";
    }
}
