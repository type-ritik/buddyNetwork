package com.buddyNetwork.BuddyNetwork.repository;

public class MyResponseObject {
    private String message;
    private int value;

    public MyResponseObject(String message, int value) {
        this.message = message;
        this.value = value;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
