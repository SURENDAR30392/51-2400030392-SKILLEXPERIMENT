package com.example.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Generic API error response")
public class ApiErrorResponse {

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Error message", example = "Student not found with id: 999")
    private String message;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
