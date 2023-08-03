package com.example.advancedmappingdemo.service;

import com.example.advancedmappingdemo.model.Review;
import com.example.advancedmappingdemo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Review save(Review review){
        return repository.save(review);
    }

}
