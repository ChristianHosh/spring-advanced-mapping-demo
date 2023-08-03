package com.example.advancedmappingdemo.model;


import jakarta.persistence.*;
import lombok.Data;

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
    private InstructorDetail instructorDetail;

}
