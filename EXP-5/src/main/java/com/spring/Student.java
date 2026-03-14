package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Value("2400030392")
    private String id;
    
    @Value("Surendar")
    private String name;
    
    @Value("Male")
    private String gender;

    @Autowired
    private Certification certification;

    public void displayDetails() {
        System.out.println("----- Student Info -----");
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Certification: " + certification);
    }
}