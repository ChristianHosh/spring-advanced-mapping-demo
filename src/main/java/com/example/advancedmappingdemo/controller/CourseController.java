package com.example.advancedmappingdemo.controller;

import com.example.advancedmappingdemo.model.Course;
import com.example.advancedmappingdemo.model.Review;
import com.example.advancedmappingdemo.service.CourseService;
import com.example.advancedmappingdemo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final ReviewService reviewService;

    @Autowired
    public CourseController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courseList = courseService.findAll();

        if (courseList.isEmpty()) {
            System.out.println("COURSE LIST IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course courseToUpdate) {

        System.out.println("UPDATING COURSE " + id);

        Course currentCourse = courseService.findById(id);

        if (currentCourse == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentCourse.setTitle(courseToUpdate.getTitle());

        currentCourse = courseService.save(currentCourse);

        return new ResponseEntity<>(currentCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id) {
        System.out.println("FINDING COURSE WITH ID " + id);

        Course courseToDelete = courseService.findById(id);

        if (courseToDelete == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        courseService.delete(courseToDelete);

        return new ResponseEntity<>(courseToDelete, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int id) {
        System.out.println("FINDING COURSE WITH ID " + id);

        Course course = courseService.findById(id);

        if (course == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<Review> saveReviewToCourse(@PathVariable("id") int id, @RequestBody Review reviewToSave) {
        System.out.println("FINDING COURSE WITH ID " + id);

        Course currentCourse = courseService.findById(id);

        if (currentCourse == null) {
            System.out.println("COURSE WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentCourse.getReviews().add(reviewToSave);

        reviewToSave = reviewService.save(reviewToSave);

        return new ResponseEntity<>(reviewToSave, HttpStatus.OK);
    }

}
