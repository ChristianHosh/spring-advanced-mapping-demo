package com.example.advancedmappingdemo.service;

import com.example.advancedmappingdemo.model.InstructorDetail;
import com.example.advancedmappingdemo.repository.InstructorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorDetailService {

    private final InstructorDetailsRepository repository;

    @Autowired
    public InstructorDetailService(InstructorDetailsRepository repository) {
        this.repository = repository;
    }


    public List<InstructorDetail> findAll(){
        return repository.findAll();
    }

    public InstructorDetail findById(int id) {
        Optional<InstructorDetail> instructor = repository.findById(id);

        return instructor.orElse(null);
    }

    public InstructorDetail save(InstructorDetail instructorToSave) {
        return repository.save(instructorToSave);
    }

    public InstructorDetail delete(InstructorDetail instructorToDelete){
        repository.deleteById(instructorToDelete.getId());
        return instructorToDelete;
    }
}
