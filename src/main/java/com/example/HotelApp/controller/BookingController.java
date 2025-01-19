package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Booking;
import com.example.HotelApp.service.interfaces.BookingService;
import com.example.HotelApp.service.interfaces.RoomService;
import com.example.HotelApp.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, UserService userService, RoomService roomService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @GetMapping("/add")
    public String addBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "booking_form";
    }
    
    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        System.out.println("Bookings retrieved: " + bookings);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @PostMapping("/add")
    public String saveBooking(@Valid @ModelAttribute Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "booking_form";
        }
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }
    @GetMapping("/edit/{id}")
    public String editBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Invalid booking ID: " + id);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "booking_form"; // Utilise le même formulaire que l'ajout
    }

    @PostMapping("/edit/{id}")
    public String updateBooking(@PathVariable Long id, @Valid @ModelAttribute Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "booking_form";
        }
        bookingService.updateBooking(id, booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }

}
