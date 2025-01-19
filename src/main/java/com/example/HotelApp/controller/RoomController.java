package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Room;
import com.example.HotelApp.service.impl.RoomServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomServiceImpl roomService;

    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String listRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "room_form";
    }

    @PostMapping("/add")
    public String saveRoom(@ModelAttribute Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room_form";
    }

    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute Room room) {
        roomService.updateRoom(id, room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
