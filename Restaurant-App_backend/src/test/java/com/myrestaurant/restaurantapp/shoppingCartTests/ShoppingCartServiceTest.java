package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.repository.ShoppingCartRepository;
import com.myrestaurant.restaurantapp.shoppingCart.service.ShoppingCartService;
import com.myrestaurant.restaurantapp.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartById() {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Optional<ShoppingCart> retrievedCart = shoppingCartService.getCartById(1L);
        assertTrue(retrievedCart.isPresent());
        assertEquals(cart, retrievedCart.get());
        verify(shoppingCartRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCartById_NotFound() {
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ShoppingCart> retrievedCart = shoppingCartService.getCartById(1L);
        assertFalse(retrievedCart.isPresent());
        verify(shoppingCartRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCart() {
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(cart);

        ShoppingCart createdCart = shoppingCartService.createCart(cart);
        assertEquals(cart, createdCart);
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    public void testDeleteCart() {
        shoppingCartService.deleteCart(1L);
        verify(shoppingCartRepository, times(1)).deleteById(1L);
    }
}
