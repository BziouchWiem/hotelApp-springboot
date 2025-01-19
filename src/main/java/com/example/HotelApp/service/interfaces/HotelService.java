package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    Hotel saveHotel(Hotel hotel);
    Hotel updateHotel(Long id, Hotel hotel);
    void deleteHotel(Long id);
}
