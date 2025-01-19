package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review getReviewById(Long id);
    Review saveReview(Review review);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
}

