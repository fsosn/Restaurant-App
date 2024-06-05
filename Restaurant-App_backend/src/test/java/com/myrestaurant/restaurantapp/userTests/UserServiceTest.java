package com.myrestaurant.restaurantapp.userTests;

import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import com.myrestaurant.restaurantapp.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "user1@example.com", "password", "John", "Doe", Role.USER);
        User user2 = new User(2L, "user2@example.com", "password", "Jane", "Doe", Role.USER);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1L);
        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);
        assertEquals(user, createdUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindByEmail() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.findByEmail("user@example.com");
        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());
        verify(userRepository, times(1)).findByEmail("user@example.com");
    }

    @Test
    public void testUpdateUserProfile() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        User updatedUserDetails = new User(1L, "user@example.com", null, "Johnny", "Doe", Role.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUserDetails);

        User updatedUser = userService.updateUserProfile(1L, updatedUserDetails);
        assertEquals("Johnny", updatedUser.getFirstName());
        assertNull(updatedUser.getPassword());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
