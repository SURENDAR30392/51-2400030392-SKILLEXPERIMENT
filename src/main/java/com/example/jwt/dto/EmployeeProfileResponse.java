package com.example.jwt.dto;

public class EmployeeProfileResponse {

    private final String username;
    private final String role;
    private final String message;

    public EmployeeProfileResponse(String username, String role, String message) {
        this.username = username;
        this.role = role;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}

