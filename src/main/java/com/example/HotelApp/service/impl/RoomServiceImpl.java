package com.example.HotelApp.service.impl;

import com.example.HotelApp.persistance.dao.RoomRepository;
import com.example.HotelApp.persistance.entities.Room;
import com.example.HotelApp.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Room existingRoom = getRoomById(id);
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setType(room.getType());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setStatus(room.getStatus());
        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
