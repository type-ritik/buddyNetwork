package com.buddyNetwork.BuddyNetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Ignore any property with Null values
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    // Change the name of the 'id' property to 'employeeId'
    @JsonProperty("employeeId")
    private Long id;

    @JsonProperty("employeeName")
    private String name;

    // Ignore the 'department' property
    @JsonIgnore
    private String department;

    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

}
