package com.example.advancedmappingdemo.repository;

import com.example.advancedmappingdemo.model.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailsRepository extends JpaRepository<InstructorDetail, Integer> {

}
