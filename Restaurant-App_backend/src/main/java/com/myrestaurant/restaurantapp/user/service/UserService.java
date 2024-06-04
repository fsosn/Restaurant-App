package com.myrestaurant.restaurantapp.user.service;

import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUserProfile(Long userId, User updatedDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (updatedDetails.getEmail() != null) {
            user.setEmail(updatedDetails.getEmail());
        }
        if (updatedDetails.getFirstName() != null) {
            user.setFirstName(updatedDetails.getFirstName());
        }
        if (updatedDetails.getLastName() != null) {
            user.setLastName(updatedDetails.getLastName());
        }
        if (updatedDetails.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }
}
