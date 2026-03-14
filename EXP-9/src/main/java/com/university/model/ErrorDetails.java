package com.university.model;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private int statusCode;

    public ErrorDetails(String message, int statusCode) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.statusCode = statusCode;
    }

    // Getters (Important for JSON conversion)
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public int getStatusCode() { return statusCode; }
}