package com.university.courseapi;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private List<Course> courseList = new ArrayList<>();

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public List<Course> getAllCourses() {
        return courseList;
    }

    public boolean updateCourse(int id, Course updatedCourse) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId() == id) {
                courseList.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        return courseList.removeIf(c -> c.getCourseId() == id);
    }
} 	