package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    public void testCartItemCreation() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        CartItem item = new CartItem(1L, cart, product, 2);

        assertEquals(1L, item.getCartItemID());
        assertEquals(cart, item.getShoppingCart());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        CartItem item = new CartItem();
        item.setQuantity(5);
        assertEquals(5, item.getQuantity());
    }

    @Test
    public void testSetCartItemID() {
        CartItem item = new CartItem();
        item.setCartItemID(1L);
        assertEquals(1L, item.getCartItemID());
    }

    @Test
    public void testSetShoppingCart() {
        CartItem item = new CartItem();
        ShoppingCart cart = new ShoppingCart();
        item.setShoppingCart(cart);
        assertEquals(cart, item.getShoppingCart());
    }

    @Test
    public void testSetProduct() {
        CartItem item = new CartItem();
        Product product = new Product();
        item.setProduct(product);
        assertEquals(product, item.getProduct());
    }

    @Test
    public void testEqualsAndHashCode() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        CartItem item1 = new CartItem(1L, cart, product, 2);
        CartItem item2 = new CartItem(1L, cart, product, 2);

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    public void testToString() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        CartItem item = new CartItem(1L, cart, product, 2);

        String expectedString = "CartItem(cartItemID=1, shoppingCart=" + cart.toString() + ", product=" + product.toString() + ", quantity=2)";
        assertEquals(expectedString, item.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        CartItem item = new CartItem();
        assertNotNull(item);
    }

    @Test
    public void testAllArgsConstructor() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        CartItem item = new CartItem(1L, cart, product, 2);

        assertEquals(1L, item.getCartItemID());
        assertEquals(cart, item.getShoppingCart());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }
}
