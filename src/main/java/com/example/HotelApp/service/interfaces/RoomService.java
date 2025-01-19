package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room saveRoom(Room room);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
}
