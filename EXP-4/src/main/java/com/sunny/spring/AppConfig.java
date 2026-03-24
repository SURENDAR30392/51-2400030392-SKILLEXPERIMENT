package com.sunny.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student annotatedStudent() {
        // Constructor Injection
        Student s = new Student(102, "Surendar", "Java", 2026);
        
        // Setter Injection
        s.setCourse("Cloud Computing (AWS)");
        s.setYear(3);
        
        return s;
    }
}