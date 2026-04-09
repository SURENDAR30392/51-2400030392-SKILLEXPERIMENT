package com.example.exp15.config;

import com.example.exp15.model.Role;
import com.example.exp15.model.User;
import com.example.exp15.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createUserIfMissing("admin", "admin123", Role.ADMIN);
        createUserIfMissing("employee", "employee123", Role.EMPLOYEE);
    }

    private void createUserIfMissing(String username, String password, Role role) {
        userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username, passwordEncoder.encode(password), role)));
    }
}
