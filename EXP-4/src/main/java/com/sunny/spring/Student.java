package com.sunny.spring;

public class Student {
    private int studentId;
    private String name;
    private String course;
    private int year;

    // Default Constructor
    public Student() {}

    // Constructor for Constructor Injection
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    // Setter methods for Setter Injection
    public void setCourse(String course) { this.course = course; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Student [ID=" + studentId + ", Name=" + name + ", Course=" + course + ", Year=" + year + "]";
    }
}