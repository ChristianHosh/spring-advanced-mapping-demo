package com.example.advancedmappingdemo.controller;

import com.example.advancedmappingdemo.model.Course;
import com.example.advancedmappingdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courseList = service.findAll();

        if (courseList.isEmpty()) {
            System.out.println("COURSE LIST IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course courseToUpdate) {

        System.out.println("UPDATING COURSE " + id);

        Course currentCourse = service.findById(id);

        if (currentCourse == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentCourse.setTitle(courseToUpdate.getTitle());

        currentCourse = service.save(currentCourse);

        return new ResponseEntity<>(currentCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id) {
        System.out.println("FINDING COURSE WITH ID " + id);

        Course courseToDelete = service.findById(id);

        if (courseToDelete == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(courseToDelete);

        return new ResponseEntity<>(courseToDelete, HttpStatus.OK);
    }


}
