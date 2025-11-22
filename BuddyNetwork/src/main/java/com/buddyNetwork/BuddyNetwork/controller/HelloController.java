package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Backend";
    }

    @GetMapping("/name")
    public String getMethodName() {
        return new String("<h1>Hello World! My name is Ritik Sharma.</h1>");
    }

}
