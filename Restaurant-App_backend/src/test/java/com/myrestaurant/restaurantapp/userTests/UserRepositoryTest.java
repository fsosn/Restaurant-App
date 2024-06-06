package com.myrestaurant.restaurantapp.userTests;

import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User(null, "user@example.com", "password", "John", "Doe", Role.USER);
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("user@example.com", savedUser.getEmail());
    }

    @Test
    public void testFindByEmail() {
        User user = new User(null, "user@example.com", "password", "John", "Doe", Role.USER);
        userRepository.save(user);

        Optional<User> retrievedUser = userRepository.findByEmail("user@example.com");
        assertTrue(retrievedUser.isPresent());
        assertEquals("user@example.com", retrievedUser.get().getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User(null, "user@example.com", "password", "John", "Doe", Role.USER);
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        assertFalse(retrievedUser.isPresent());
    }
}

