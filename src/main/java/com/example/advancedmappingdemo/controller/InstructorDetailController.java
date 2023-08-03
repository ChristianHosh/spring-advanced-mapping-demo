package com.example.advancedmappingdemo.controller;

import com.example.advancedmappingdemo.model.InstructorDetail;
import com.example.advancedmappingdemo.service.InstructorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor-details")
public class InstructorDetailController {

    private final InstructorDetailService service;

    @Autowired
    public InstructorDetailController(InstructorDetailService service) {
        this.service = service;
    }


    // GET ALL INSTRUCTORS
    @GetMapping("")
    public ResponseEntity<List<InstructorDetail>> getAllDetails() {
        List<InstructorDetail> instructorList = service.findAll();

        if (instructorList.isEmpty()){
            System.out.println("INSTRUCTORS LIST IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }

    // GET INSTRUCTOR BY ID
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDetail> getInstructorDetailById(@PathVariable("id") int id) {
        System.out.println("FINDING INSTRUCTOR DETAIL WITH ID " + id);
        InstructorDetail detail = service.findById(id);

        if (detail == null){
            System.out.println("INSTRUCTOR DETAIL WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<InstructorDetail> saveInstructorDetail(@RequestBody InstructorDetail detailToSave) {
        System.out.println("CREATING NEW INSTRUCTOR DETAIL INSTANCE");
        InstructorDetail savedDetail = service.save(detailToSave);

        if (savedDetail == null) {
            System.out.println("COULD NOT CREATE NEW INSTRUCTOR DETAIL");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorDetail> updateInstructorDetail(@PathVariable("id") int id, @RequestBody InstructorDetail detail) {
        System.out.println("UPDATING INSTRUCTOR DETAIL " + id);

        InstructorDetail currentDetail = service.findById(id);

        if (currentDetail == null) {
            System.out.println("INSTRUCTOR DETAIL WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentDetail.setYoutubeChannel(detail.getYoutubeChannel());
        currentDetail.setHobby(detail.getHobby());

        currentDetail = service.save(currentDetail);
        return new ResponseEntity<>(currentDetail, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InstructorDetail> deleteInstructorDetail(@PathVariable("id") int id){
        System.out.println("DELETING INSTRUCTOR DETAIL " + id);

        InstructorDetail currentDetail = service.findById(id);

        if (currentDetail == null) {
            System.out.println("INSTRUCTOR DETAIL WITH ID " + id + " NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentDetail = service.delete(currentDetail);
        return new ResponseEntity<>(currentDetail, HttpStatus.OK);
    }
}
