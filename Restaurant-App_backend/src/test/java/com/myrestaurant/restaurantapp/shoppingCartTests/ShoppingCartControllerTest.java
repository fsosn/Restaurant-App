package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.shoppingCart.controller.ShoppingCartController;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartById() {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartService.getCartById(1L)).thenReturn(Optional.of(cart));

        ResponseEntity<ShoppingCart> response = shoppingCartController.getCartById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
        verify(shoppingCartService, times(1)).getCartById(1L);
    }

    @Test
    public void testGetCartById_NotFound() {
        when(shoppingCartService.getCartById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ShoppingCart> response = shoppingCartController.getCartById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shoppingCartService, times(1)).getCartById(1L);
    }

    @Test
    public void testCreateCart() {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartService.createCart(any(ShoppingCart.class))).thenReturn(cart);

        ShoppingCart createdCart = shoppingCartController.createCart(cart);
        assertEquals(cart, createdCart);
        verify(shoppingCartService, times(1)).createCart(any(ShoppingCart.class));
    }

    @Test
    public void testDeleteCart() {
        ResponseEntity<?> response = shoppingCartController.deleteCart(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(shoppingCartService, times(1)).deleteCart(1L);
    }

    @Test
    public void testDeleteCart_NotFound() {
        doThrow(new IllegalArgumentException("Cart not found")).when(shoppingCartService).deleteCart(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCartController.deleteCart(1L);
        });

        assertEquals("Cart not found", exception.getMessage());
        verify(shoppingCartService, times(1)).deleteCart(1L);
    }

    @Test
    public void testGetCartById_Exception() {
        when(shoppingCartService.getCartById(1L)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            shoppingCartController.getCartById(1L);
        });

        assertEquals("Database error", exception.getMessage());
        verify(shoppingCartService, times(1)).getCartById(1L);
    }

    @Test
    public void testCreateCart_Exception() {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartService.createCart(any(ShoppingCart.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            shoppingCartController.createCart(cart);
        });

        assertEquals("Database error", exception.getMessage());
        verify(shoppingCartService, times(1)).createCart(any(ShoppingCart.class));
    }
}
