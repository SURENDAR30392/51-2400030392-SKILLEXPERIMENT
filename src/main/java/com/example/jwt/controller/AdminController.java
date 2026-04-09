package com.example.jwt.controller;

import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody User user) {
        user.setRole("EMPLOYEE");
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted");
    }
}
