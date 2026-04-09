package com.example.exp15.controller;

import com.example.exp15.dto.EmployeeProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/profile")
    public ResponseEntity<EmployeeProfileResponse> getProfile(Authentication authentication) {
        return ResponseEntity.ok(
                new EmployeeProfileResponse(
                        authentication.getName(),
                        "EMPLOYEE",
                        "Profile details fetched successfully"));
    }
}
