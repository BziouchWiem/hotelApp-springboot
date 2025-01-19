package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking saveBooking(Booking booking);
    Booking updateBooking(Long id, Booking booking);
    void deleteBooking(Long id);
}
