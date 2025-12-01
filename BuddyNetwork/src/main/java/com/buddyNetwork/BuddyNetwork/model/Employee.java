package com.buddyNetwork.BuddyNetwork.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Ignore any property with Null values
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    // Change the name of the 'id' property to 'employeeId'
    @JsonAlias({ "employeeId", "employee_id", "id" })
    private Long id;

    @JsonProperty("employeeName")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    // Ignore the 'department' property
    @JsonIgnore
    private String department;

    public Employee(Long id, String name, String department, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
