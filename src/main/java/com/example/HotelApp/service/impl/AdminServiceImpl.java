package com.example.HotelApp.service.impl;

import com.example.HotelApp.persistance.dao.AdminRepository;
import com.example.HotelApp.persistance.entities.Admin;
import com.example.HotelApp.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        // Vérifier si l'email existe déjà dans la base de données avant de sauvegarder
        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin existingAdmin = getAdminById(id);
        
        // Vérifier si l'email existe déjà avant de le mettre à jour
        if (!existingAdmin.getEmail().equals(admin.getEmail()) && adminRepository.findByEmail(admin.getEmail()) != null) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        // Mettre à jour les informations de l'admin
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setEmail(admin.getEmail());
        
        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
