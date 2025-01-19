package com.example.HotelApp.persistance.dao;

import com.example.HotelApp.persistance.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByEmail(String email);  // Ajouter cette méthode pour rechercher par email
}
