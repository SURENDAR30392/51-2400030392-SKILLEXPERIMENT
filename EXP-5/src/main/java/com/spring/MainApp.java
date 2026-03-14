package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // Load IoC Container
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Student Bean
        Student student = context.getBean(Student.class);

        // Execute method
        student.displayDetails();

        // Close context
        context.close();
    }
}