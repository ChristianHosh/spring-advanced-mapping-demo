package com.example.advancedmappingdemo.repository;

import com.example.advancedmappingdemo.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    List<Instructor> findInstructorsByLastName(String lastName);

    List<Instructor> findInstructorsByLastNameContaining(String subLastName);
}
