package com.example.advancedmappingdemo.controller;

import com.example.advancedmappingdemo.model.Instructor;
import com.example.advancedmappingdemo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService service;

    @Autowired
    public InstructorController(InstructorService service) {
        this.service = service;
    }


    // GET ALL INSTRUCTORS
    @GetMapping("")
    public ResponseEntity<List<Instructor>> getAll() {
        List<Instructor> instructorList = service.findAll();

        if (instructorList.isEmpty()){
            System.out.println("INSTRUCTORS LIST IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }

    // GET INSTRUCTOR BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getById(@PathVariable("id") int id) {
        System.out.println("FINDING INSTRUCTOR WITH ID " + id);
        Instructor instructor = service.findById(id);

        if (instructor == null){
            System.out.println("INSTRUCTOR WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructorToSave) {
        System.out.println("CREATING NEW INSTRUCTOR INSTANCE");
        Instructor savedInstructor = service.save(instructorToSave);

        if (savedInstructor == null) {
            System.out.println("COULD NOT CREATE NEW INSTRUCTOR");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedInstructor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") int id, @RequestBody Instructor instructor) {
        System.out.println("UPDATING INSTRUCTOR " + id);

        Instructor currentInstructor = service.findById(id);

        if (currentInstructor == null) {
            System.out.println("INSTRUCTOR WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentInstructor.setFirstName(instructor.getFirstName());
        currentInstructor.setLastName(instructor.getLastName());
        currentInstructor.setEmail(instructor.getEmail());

        currentInstructor = service.save(currentInstructor);
        return new ResponseEntity<>(currentInstructor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> deleteInstructor(@PathVariable("id") int id){
        System.out.println("DELETING INSTRUCTOR " + id);

        Instructor currentInstructor = service.findById(id);

        if (currentInstructor == null) {
            System.out.println("INSTRUCTOR WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentInstructor = service.delete(currentInstructor);
        return new ResponseEntity<>(currentInstructor, HttpStatus.OK);
    }
}
