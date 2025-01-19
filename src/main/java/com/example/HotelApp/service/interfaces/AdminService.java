package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    Admin saveAdmin(Admin admin);
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
}
