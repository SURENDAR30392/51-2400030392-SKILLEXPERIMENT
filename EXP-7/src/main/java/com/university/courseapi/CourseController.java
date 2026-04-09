package com.university.courseapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // POST: Create a course
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return new ResponseEntity<>("Course added successfully!", HttpStatus.CREATED);
    }

    // GET: View all courses
    @GetMapping("/viewall")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    // PUT: Update a course
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course course) {
        if (courseService.updateCourse(id, course)) {
            return new ResponseEntity<>("Course updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Course ID not found!", HttpStatus.NOT_FOUND);
    }

    // DELETE: Remove a course
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        if (courseService.deleteCourse(id)) {
            return new ResponseEntity<>("Course deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid ID!", HttpStatus.BAD_REQUEST);
    }

    // GET: Search by title
    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable String title) {
        List<Course> result = courseService.getAllCourses().stream()
                .filter(c -> c.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        
        if (result.isEmpty()) {
            return new ResponseEntity<>("No course found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}