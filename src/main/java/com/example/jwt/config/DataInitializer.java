package com.example.jwt.config;

import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initUsers(UserService userService) {
        return args -> {
            if (userService.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("adminpass");
                admin.setRole("ADMIN");
                userService.saveUser(admin);
            }
            if (userService.findByUsername("employee").isEmpty()) {
                User emp = new User();
                emp.setUsername("employee");
                emp.setPassword("emppass");
                emp.setRole("EMPLOYEE");
                userService.saveUser(emp);
            }
        };
    }
}
