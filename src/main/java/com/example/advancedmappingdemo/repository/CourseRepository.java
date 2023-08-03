package com.example.advancedmappingdemo.repository;

import com.example.advancedmappingdemo.model.Course;
import com.example.advancedmappingdemo.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public List<Course> findCoursesByInstructor(Instructor instructor);

}
