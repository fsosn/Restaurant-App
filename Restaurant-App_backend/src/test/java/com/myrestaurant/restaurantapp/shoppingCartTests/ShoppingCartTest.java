package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.user.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    public void testShoppingCartCreation() {
        User user = new User();
        CartItem item = new CartItem();
        ShoppingCart cart = new ShoppingCart(1L, user, Arrays.asList(item));

        assertEquals(1L, cart.getCartID());
        assertEquals(user, cart.getUser());
        assertEquals(1, cart.getCartItems().size());
        assertEquals(item, cart.getCartItems().get(0));
    }

    @Test
    public void testSetUser() {
        ShoppingCart cart = new ShoppingCart();
        User user = new User();
        cart.setUser(user);
        assertEquals(user, cart.getUser());
    }

    @Test
    public void testAddCartItem() {
        ShoppingCart cart = new ShoppingCart();
        CartItem item = new CartItem();
        cart.setCartItems(Arrays.asList(item));
        assertEquals(1, cart.getCartItems().size());
        assertEquals(item, cart.getCartItems().get(0));
    }

    @Test
    public void testSetCartItems() {
        ShoppingCart cart = new ShoppingCart();
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        cart.setCartItems(Arrays.asList(item1, item2));
        assertEquals(2, cart.getCartItems().size());
        assertTrue(cart.getCartItems().contains(item1));
        assertTrue(cart.getCartItems().contains(item2));
    }

    @Test
    public void testGetCartID() {
        ShoppingCart cart = new ShoppingCart();
        cart.setCartID(2L);
        assertEquals(2L, cart.getCartID());
    }

    @Test
    public void testEmptyCartItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.setCartItems(Collections.emptyList());
        assertTrue(cart.getCartItems().isEmpty());
    }
}
