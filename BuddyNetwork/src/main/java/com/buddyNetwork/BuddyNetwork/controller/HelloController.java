package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddyNetwork.BuddyNetwork.repository.ErrorResponse;
import com.buddyNetwork.BuddyNetwork.repository.MyResponseObject;
import com.buddyNetwork.BuddyNetwork.repository.UserRepo;

import jakarta.servlet.http.HttpSession;
import com.buddyNetwork.BuddyNetwork.model.Employee;

@RestController
@RequestMapping("/api")
public class HelloController {

    // HTTP GET: /hello request (simple hello java)
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Backend";
    }

    // HTTP GET: /id/{id} request (params id)
    @GetMapping("/id/{id}")
    public String getMethodName(@PathVariable("id") Long userId) {
        return String.format("<h1>Hello World! My name is %d.</h1>", userId);
    }

    // HTTP GET: /data request (user data request)
    @GetMapping("/data")
    public ResponseEntity<MyResponseObject> getDetail() {
        MyResponseObject data = new MyResponseObject("Welcome back Ritik", 123);

        // Return a 200 OK status with the JSON body
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // HTTP GET: /error request (custom error page)
    @GetMapping("/error")
    public ResponseEntity<ErrorResponse> getError() {
        ErrorResponse error = new ErrorResponse("Resource not found.", 404);

        // Return a 404 Not Found status with the JSON error body
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // HTTP POST: /users request (Post user data to server)
    @PostMapping("/users")
    public ResponseEntity<String> postName(@RequestBody UserRepo user) {
        String name = user.getFullName();

        System.out.println("Received name: " + name);
        return ResponseEntity.ok("User created with name: " + name);
    }

    // HTTP POST: /path request (Post user name and say Hello to Client)
    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        System.out.println(entity);
        return "Hello " + entity;
    }

    @GetMapping("/all/headers")
    public String getAllHeaders(@RequestHeader MultiValueMap<String, String> headers) {
        headers.forEach((key, value) -> System.out.println(key + ": " + value));
        return "All headers printed to console!";
    }

    @GetMapping("/chunck/header")
    public String getHeader(@RequestHeader("User-Agent") String userAgent,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        System.out.println("User-Agent: " + userAgent);
        if (authorizationHeader != null) {
            System.out.println("Authorization: " + authorizationHeader);
        } else {
            System.out.println("Authorization header not present.");
        }
        return "Data retrieved successfully!";
    }

    @GetMapping("/set/session")
    public String setSession(HttpSession session) {
        session.setAttribute("token", "Mini Skirt");
        return "Session set successfully!";
    }

    @GetMapping("/get/session")
    public String getSession(HttpSession session) {
        String ssion = (String) session.getAttribute("token");
        System.err.println(ssion);
        return ssion;
    }

    @GetMapping("/employee")
    public Employee getEmployee() {
        // Employee emp = new Employee(1L, "Ritik", "Software Engineering");

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Ritik");
        emp.setDepartment("Software Engineering");

        return emp;
        // return new Employee(1L, "Ritik", "Software Engineering");
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        Employee emp = new Employee();
        emp.setId(newEmployee.getId());
        emp.setName(newEmployee.getName());
        emp.setDepartment(newEmployee.getDepartment());

        return emp;
    }
}
