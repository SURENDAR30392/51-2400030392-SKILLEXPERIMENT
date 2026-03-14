package com.university.controller;

import com.university.exception.InvalidInputException;
import com.university.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public String getStudentDetails(@PathVariable String id) {
        // Logic to trigger InvalidInputException
        if (!id.matches("\\d+")) {
            throw new InvalidInputException("Invalid ID format! Please enter numbers only.");
        }

        // Logic to trigger StudentNotFoundException
        if (!id.equals("101")) {
            throw new StudentNotFoundException("Student with ID " + id + " does not exist.");
        }

        return "Student Name: John Doe, Course: Java Fullstack";
    }
}