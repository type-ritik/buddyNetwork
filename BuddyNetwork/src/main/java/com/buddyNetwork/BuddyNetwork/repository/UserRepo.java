package com.buddyNetwork.BuddyNetwork.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRequest{name='" + name + "'}";
    }
}
