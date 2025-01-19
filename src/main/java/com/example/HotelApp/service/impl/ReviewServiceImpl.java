package com.example.HotelApp.service.impl;

import com.example.HotelApp.persistance.dao.ReviewRepository;
import com.example.HotelApp.persistance.entities.Review;
import com.example.HotelApp.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review existingReview = getReviewById(id);
        existingReview.setContent(review.getContent());
        existingReview.setRating(review.getRating());
        existingReview.setUser(review.getUser());
        existingReview.setHotel(review.getHotel());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
