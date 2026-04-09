package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Management", description = "CRUD APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(
            summary = "Add a new student",
            description = "Creates a student record after validating the request body."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = Student.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error in the submitted student data",
                    content = @Content(schema = @Schema(implementation = com.example.studentmanagement.dto.ValidationErrorResponse.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Student details to create",
            content = @Content(
                    schema = @Schema(implementation = Student.class),
                    examples = @ExampleObject(
                            name = "CreateStudent",
                            value = """
                                    {
                                      "name": "Ananya Reddy",
                                      "email": "ananya.reddy@example.com",
                                      "course": "Full Stack Development"
                                    }
                                    """
                    )
            )
    )
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all students",
            description = "Returns the complete list of students stored in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Students retrieved successfully",
            content = @Content(schema = @Schema(implementation = Student.class))
    )
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a student by ID",
            description = "Fetches one student using the provided student ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student found",
                    content = @Content(schema = @Schema(implementation = Student.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found for the given ID",
                    content = @Content(schema = @Schema(implementation = com.example.studentmanagement.dto.ApiErrorResponse.class))
            )
    })
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a student",
            description = "Updates the student details for the given ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = Student.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error in the submitted student data",
                    content = @Content(schema = @Schema(implementation = com.example.studentmanagement.dto.ValidationErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found for the given ID",
                    content = @Content(schema = @Schema(implementation = com.example.studentmanagement.dto.ApiErrorResponse.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Updated student details",
            content = @Content(
                    schema = @Schema(implementation = Student.class),
                    examples = @ExampleObject(
                            name = "UpdateStudent",
                            value = """
                                    {
                                      "name": "Ananya Reddy",
                                      "email": "ananya.reddy@example.com",
                                      "course": "Advanced Java"
                                    }
                                    """
                    )
            )
    )
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a student",
            description = "Deletes the student record for the given ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Student deleted successfully", content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found for the given ID",
                    content = @Content(schema = @Schema(implementation = com.example.studentmanagement.dto.ApiErrorResponse.class))
            )
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
