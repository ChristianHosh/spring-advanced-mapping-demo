package com.example.advancedmappingdemo.controller;

import com.example.advancedmappingdemo.model.Course;
import com.example.advancedmappingdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if (courseList.isEmpty()){
            System.out.println("COURSE LIST IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }


}
