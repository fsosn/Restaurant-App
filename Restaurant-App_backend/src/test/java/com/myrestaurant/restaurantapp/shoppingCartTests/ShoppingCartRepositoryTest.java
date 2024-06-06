package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.repository.ShoppingCartRepository;
import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveShoppingCart() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        Product product = new Product();
        product.setName("Test Product");
        CartItem item = new CartItem(null, null, product, 2);
        ShoppingCart cart = new ShoppingCart(null, user, Arrays.asList(item));
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

        assertNotNull(savedCart.getCartID());
        assertEquals(user, savedCart.getUser());
        assertEquals(1, savedCart.getCartItems().size());
        assertEquals(2, savedCart.getCartItems().get(0).getQuantity());
    }

    @Test
    public void testFindShoppingCartById() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        Product product = new Product();
        product.setName("Test Product");
        CartItem item = new CartItem(null, null, product, 2);
        ShoppingCart cart = new ShoppingCart(null, user, Arrays.asList(item));
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

        Optional<ShoppingCart> retrievedCart = shoppingCartRepository.findById(savedCart.getCartID());
        assertTrue(retrievedCart.isPresent());
        assertEquals(user, retrievedCart.get().getUser());
        assertEquals(1, retrievedCart.get().getCartItems().size());
        assertEquals(2, retrievedCart.get().getCartItems().get(0).getQuantity());
    }

    @Test
    public void testDeleteShoppingCart() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        Product product = new Product();
        product.setName("Test Product");
        CartItem item = new CartItem(null, null, product, 2);
        ShoppingCart cart = new ShoppingCart(null, user, Arrays.asList(item));
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

        shoppingCartRepository.deleteById(savedCart.getCartID());
        Optional<ShoppingCart> retrievedCart = shoppingCartRepository.findById(savedCart.getCartID());
        assertFalse(retrievedCart.isPresent());
    }

    @Test
    public void testUpdateShoppingCart() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        Product product1 = new Product();
        product1.setName("Test Product 1");
        Product product2 = new Product();
        product2.setName("Test Product 2");

        CartItem item1 = new CartItem(null, null, product1, 2);
        ShoppingCart cart = new ShoppingCart(null, user, new ArrayList<>(Arrays.asList(item1)));
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

        CartItem item2 = new CartItem(null, null, product2, 3);
        List<CartItem> updatedItems = new ArrayList<>(savedCart.getCartItems());
        updatedItems.add(item2);
        savedCart.setCartItems(updatedItems);

        ShoppingCart updatedCart = shoppingCartRepository.save(savedCart);

        assertEquals(2, updatedCart.getCartItems().size());
        assertEquals(3, updatedCart.getCartItems().get(1).getQuantity());
    }

    @Test
    public void testIsEmptyShoppingCart() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        ShoppingCart cart = new ShoppingCart(null, user, new ArrayList<>());
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

        Optional<ShoppingCart> retrievedCart = shoppingCartRepository.findById(savedCart.getCartID());
        assertTrue(retrievedCart.isPresent());
        assertTrue(retrievedCart.get().getCartItems().isEmpty());
    }
}
