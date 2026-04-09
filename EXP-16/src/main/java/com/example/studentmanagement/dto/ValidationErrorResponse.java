package com.example.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.LinkedHashMap;
import java.util.Map;

@Schema(description = "Validation error response")
public class ValidationErrorResponse extends ApiErrorResponse {

    @Schema(
            description = "Field-level validation messages",
            example = "{\"email\":\"Email must be valid\",\"name\":\"Name is required\"}"
    )
    private Map<String, String> errors = new LinkedHashMap<>();

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(int status, String message) {
        super(status, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void addError(String field, String message) {
        this.errors.put(field, message);
    }
}
