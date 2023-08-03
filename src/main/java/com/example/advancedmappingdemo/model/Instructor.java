package com.example.advancedmappingdemo.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@Data
public class Instructor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_details_id")
    @JsonManagedReference
    private InstructorDetail instructorDetails;

    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Course> courses;

    public void addCourse(Course courseToAdd){
        if (courses == null){
            courses = new ArrayList<>();
        }

        courses.add(courseToAdd);

        courseToAdd.setInstructor(this);
    }

}
