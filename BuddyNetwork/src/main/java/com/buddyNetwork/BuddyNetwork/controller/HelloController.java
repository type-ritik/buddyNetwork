package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddyNetwork.BuddyNetwork.repository.UserRepo;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Backend";
    }

    @GetMapping("/id/{id}")
    public String getMethodName(@PathVariable("id") Long userId) {
        return String.format("<h1>Hello World! My name is %d.</h1>", userId);
    }

    @PostMapping("/users")
    public ResponseEntity<String> postName(@RequestBody UserRepo user) {
        String name = user.getName();

        System.out.println("Received name: " + name);
        return ResponseEntity.ok("User created with name: " + name);
    }

}
