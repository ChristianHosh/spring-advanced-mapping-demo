package com.example.advancedmappingdemo.service;

import com.example.advancedmappingdemo.model.Course;
import com.example.advancedmappingdemo.model.Instructor;
import com.example.advancedmappingdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course findById(int id){
        Optional<Course> course = repository.findById(id);

        return course.orElse(null);
    }

    public List<Course> findAll(){
        return repository.findAll();
    }

    public Course save(Course courseToSave){
        return repository.save(courseToSave);
    }

    public List<Course> findCoursesByInstructor(Instructor instructor){
        return repository.findCoursesByInstructor(instructor);
    }

}
