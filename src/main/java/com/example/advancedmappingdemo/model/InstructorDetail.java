package com.example.advancedmappingdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instructor_details")
@Data
public class InstructorDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;
}
