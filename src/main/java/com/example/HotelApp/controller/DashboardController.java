package com.example.HotelApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    // Handle GET request for /dashboard to display the dashboard page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Render the dashboard.html page
    }

    // Handle POST request for /dashboard (form submission)
    @PostMapping("/dashboard")
    public String handleFormSubmission() {
        // Process form data (for example, handle login or signup)
        
        // Redirect to the dashboard after successful form submission
        return "redirect:/dashboard"; // Redirect to /dashboard after form processing
    }
}
