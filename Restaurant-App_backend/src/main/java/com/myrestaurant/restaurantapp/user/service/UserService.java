package com.myrestaurant.restaurantapp.user.service;

import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userID) {
        return userRepository.findById(userID);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userID, User userDetails) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userID));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }
}
