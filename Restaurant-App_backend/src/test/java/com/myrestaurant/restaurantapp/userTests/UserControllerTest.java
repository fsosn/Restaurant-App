package com.myrestaurant.restaurantapp.userTests;

import com.myrestaurant.restaurantapp.user.controller.UserController;
import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "user1@example.com", "password", "John", "Doe", Role.USER);
        User user2 = new User(2L, "user2@example.com", "password", "Jane", "Doe", Role.USER);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userController.getAllUsers();
        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        when(userService.createUser(any(User.class))).thenReturn(user);

        User createdUser = userController.createUser(user);
        assertEquals(user, createdUser);
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testUpdateUserProfile() {
        User user = new User(1L, "user@example.com", "password", "John", "Doe", Role.USER);
        User updatedUser = new User(1L, "user@example.com", "newpassword", "John", "Doe", Role.USER);

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(user.getEmail());
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userService.updateUserProfile(user.getId(), updatedUser)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUserProfile(updatedUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(userService, times(1)).findByEmail(user.getEmail());
        verify(userService, times(1)).updateUserProfile(user.getId(), updatedUser);
    }

    @Test
    public void testUpdateUserProfile_UserNotFound() {
        User updatedUser = new User(1L, "user@example.com", "newpassword", "John", "Doe", Role.USER);

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("nonexistent@example.com");
        when(userService.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUserProfile(updatedUser);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(userService, times(1)).findByEmail("nonexistent@example.com");
        verify(userService, times(0)).updateUserProfile(anyLong(), any(User.class));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void testDeleteUser_NotFound() {
        doThrow(new RuntimeException()).when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }
}
