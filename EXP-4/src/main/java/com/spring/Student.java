package com.spring;

public class Student {
    private int studentId;
    private String name;
    private String course;
    private int year;

    // Default Constructor
    public Student() {}

    // Task 2: Full Constructor for Constructor Injection
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    // Task 3: Setters for Setter Injection
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Student Detail -> [ID: " + studentId + ", Name: " + name + 
               ", Course: " + course + ", Year: " + year + "]";
    }
}