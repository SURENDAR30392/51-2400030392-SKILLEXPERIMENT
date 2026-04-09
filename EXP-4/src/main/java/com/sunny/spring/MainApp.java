package com.sunny.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        
        // 1. Loading via XML
        System.out.println("--- XML Container Output ---");
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student s1 = (Student) xmlContext.getBean("xmlStudent");
        System.out.println(s1);

        // 2. Loading via Annotation
        System.out.println("\n--- Annotation Container Output ---");
        AnnotationConfigApplicationContext annoContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s2 = annoContext.getBean(Student.class);
        System.out.println(s2);
        
        annoContext.close();
    }
}