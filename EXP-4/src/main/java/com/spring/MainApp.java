package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        
        System.out.println("----- XML Configuration Output -----");
        // Load XML Context
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student s1 = (Student) xmlContext.getBean("studentXML");
        System.out.println(s1);

        System.out.println("\n----- Annotation Configuration Output -----");
        // Load Annotation Context
        ApplicationContext annoContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s2 = (Student) annoContext.getBean("studentAnnotated");
        System.out.println(s2);
        
        // Close the containers
        ((ClassPathXmlApplicationContext)xmlContext).close();
        ((AnnotationConfigApplicationContext)annoContext).close();
    }
}