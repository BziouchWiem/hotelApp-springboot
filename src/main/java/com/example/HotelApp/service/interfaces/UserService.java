package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

