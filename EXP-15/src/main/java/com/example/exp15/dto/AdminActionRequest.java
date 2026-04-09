package com.example.exp15.dto;

import jakarta.validation.constraints.NotBlank;

public class AdminActionRequest {

    @NotBlank
    private String employeeName;

    @NotBlank
    private String department;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
