package com.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Certification {
    @Value("CERT-101")
    private String id;
    
    @Value("Java Full Stack")
    private String name;
    
    @Value("2026-05-20")
    private String dateOfCompletion;

    @Override
    public String toString() {
        return "Certification [ID=" + id + ", Name=" + name + ", Date=" + dateOfCompletion + "]";
    }
}