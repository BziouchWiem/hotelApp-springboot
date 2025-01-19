package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Hotel;
import com.example.HotelApp.service.impl.HotelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelService;

    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    // Afficher la liste des hôtels
    @GetMapping
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotels"; // Vue listant tous les hôtels
    }

    // Formulaire pour ajouter un hôtel
    @GetMapping("/add")
    public String addHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel_form"; // Vue pour ajouter un hôtel
    }

    // Sauvegarder un hôtel
    @PostMapping("/add")
    public String saveHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels"; // Rediriger vers la liste des hôtels
    }

    // Formulaire de modification d'un hôtel
    @GetMapping("/edit/{id}")
    public String editHotelForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        return "hotel_form"; // Vue pour modifier un hôtel
    }

    // Sauvegarder les modifications
    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel);
        return "redirect:/hotels"; // Rediriger vers la liste des hôtels
    }

    // Supprimer un hôtel
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotels"; // Rediriger vers la liste des hôtels
    }
    
}
