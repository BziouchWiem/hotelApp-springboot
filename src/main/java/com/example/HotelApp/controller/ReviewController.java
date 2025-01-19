package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Hotel;
import com.example.HotelApp.persistance.entities.Review;
import com.example.HotelApp.persistance.entities.User;
import com.example.HotelApp.service.interfaces.HotelService;  // Importer l'interface
import com.example.HotelApp.service.interfaces.UserService;   // Importer l'interface
import com.example.HotelApp.service.impl.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;
    private final UserService userService;
    private final HotelService hotelService;

    // Constructor with dependencies
    public ReviewController(ReviewServiceImpl reviewService, UserService userService, HotelService hotelService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.hotelService = hotelService;
    }

    // Display the list of reviews
    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    // Form to add a new review
    @GetMapping("/add")
    public String addReviewForm(Model model) {
        // Fetch the lists of users and hotels
        List<User> users = userService.getAllUsers();
        List<Hotel> hotels = hotelService.getAllHotels();

        // Add them to the model
        model.addAttribute("users", users);
        model.addAttribute("hotels", hotels);
        model.addAttribute("review", new Review()); // Empty form

        return "review_form"; // Return the review_form.html view
    }

    // Save a review
    @PostMapping("/add")
    public String saveReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    // Edit an existing review
    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        List<User> users = userService.getAllUsers();
        List<Hotel> hotels = hotelService.getAllHotels();

        model.addAttribute("users", users);
        model.addAttribute("hotels", hotels);
        model.addAttribute("review", review);

        return "review_form";
    }

    // Update an existing review
    @PostMapping("/edit/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review review) {
        reviewService.updateReview(id, review);
        return "redirect:/reviews";
    }

    // Delete a review
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}
