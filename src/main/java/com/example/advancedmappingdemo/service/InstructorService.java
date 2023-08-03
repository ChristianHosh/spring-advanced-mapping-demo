package com.example.advancedmappingdemo.service;

import com.example.advancedmappingdemo.model.Instructor;
import com.example.advancedmappingdemo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository repository;

    @Autowired
    public InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public List<Instructor> findAll(){
        return repository.findAll();
    }

    public Instructor findById(int id) {
        Optional<Instructor> instructor = repository.findById(id);

        return instructor.orElse(null);
    }

    public Instructor save(Instructor instructorToSave) {
        return repository.save(instructorToSave);
    }

    public Instructor delete(Instructor instructorToDelete){
        repository.deleteById(instructorToDelete.getId());
        return instructorToDelete;
    }
}
