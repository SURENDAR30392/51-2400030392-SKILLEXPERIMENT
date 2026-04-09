package com.example.jwt.controller;

import com.example.jwt.dto.AdminActionRequest;
import com.example.jwt.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addEmployee(@Valid @RequestBody AdminActionRequest request) {
        String message = "Employee record created for " + request.getEmployeeName()
                + " in " + request.getDepartment() + " department";
        return ResponseEntity.ok(new MessageResponse(message));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(new MessageResponse("Employee record deleted for id " + employeeId));
    }
}

