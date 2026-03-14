package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student studentAnnotated() {
        Student s = new Student();
        // Task 4b: Annotation Config with Setter Injection
        s.setStudentId(202);
        s.setName("Anjali Verma");
        s.setCourse("Data Science");
        s.setYear(2025);
        return s;
    }
}