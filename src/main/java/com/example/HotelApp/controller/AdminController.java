package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Admin;
import com.example.HotelApp.service.impl.AdminServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String listAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admins";
    }

    @GetMapping("/add")
    public String addAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin_form";
    }

    @PostMapping("/add")
    public String saveAdmin(@ModelAttribute Admin admin, Model model) {
        try {
            adminService.saveAdmin(admin);  // Sauvegarde un nouvel admin
            return "redirect:/admins";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Email already exists");
            model.addAttribute("admin", admin);  // Recharger les données du formulaire
            return "admin_form";  // Reste sur la page de formulaire avec l'erreur
        }
    }

    @GetMapping("/edit/{id}")
    public String editAdminForm(@PathVariable Long id, Model model) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);  // Précharge l'admin dans le formulaire
        return "admin_form";
    }

    // Utilise POST pour mettre à jour l'admin existant (avec l'ID)
    @PostMapping("/edit/{id}")
    public String updateAdmin(@PathVariable Long id, @ModelAttribute Admin admin, Model model) {
        try {
            adminService.updateAdmin(id, admin);  // Mise à jour de l'admin existant
            return "redirect:/admins";  // Redirige vers la liste après la mise à jour
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Email already exists");
            model.addAttribute("admin", admin);  // Recharger les données du formulaire
            return "admin_form";  // Reste sur la page de formulaire avec l'erreur
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admins";
    }
}
