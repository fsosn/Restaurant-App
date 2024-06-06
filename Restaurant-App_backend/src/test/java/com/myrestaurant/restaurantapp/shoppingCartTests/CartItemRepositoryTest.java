package com.myrestaurant.restaurantapp.shoppingCartTests;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.repository.CartItemRepository;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.repository.ShoppingCartRepository;
import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveCartItem() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        product = productRepository.save(product);
        CartItem item = new CartItem(null, cart, product, 2);
        CartItem savedItem = cartItemRepository.save(item);
        assertNotNull(savedItem.getCartItemID());
    }

    @Test
    public void testFindCartItemById() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        product = productRepository.save(product);
        CartItem item = new CartItem(null, cart, product, 2);
        CartItem savedItem = cartItemRepository.save(item);
        Optional<CartItem> retrievedItem = cartItemRepository.findById(savedItem.getCartItemID());
        assertTrue(retrievedItem.isPresent());
        assertEquals(2, retrievedItem.get().getQuantity());
    }

    @Test
    public void testUpdateCartItemQuantity() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        product = productRepository.save(product);
        CartItem item = new CartItem(null, cart, product, 2);
        CartItem savedItem = cartItemRepository.save(item);

        savedItem.setQuantity(5);
        CartItem updatedItem = cartItemRepository.save(savedItem);

        Optional<CartItem> retrievedItem = cartItemRepository.findById(updatedItem.getCartItemID());
        assertTrue(retrievedItem.isPresent());
        assertEquals(5, retrievedItem.get().getQuantity());
    }

    @Test
    public void testDeleteCartItem() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        product = productRepository.save(product);
        CartItem item = new CartItem(null, cart, product, 2);
        CartItem savedItem = cartItemRepository.save(item);

        cartItemRepository.deleteById(savedItem.getCartItemID());

        Optional<CartItem> retrievedItem = cartItemRepository.findById(savedItem.getCartItemID());
        assertFalse(retrievedItem.isPresent());
    }

    @Test
    public void testFindCartItemsByCartId() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart = shoppingCartRepository.save(cart);

        Product product1 = new Product();
        Product product2 = new Product();
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        CartItem item1 = new CartItem(null, cart, product1, 2);
        CartItem item2 = new CartItem(null, cart, product2, 3);
        cartItemRepository.save(item1);
        cartItemRepository.save(item2);

        List<CartItem> cartItems = cartItemRepository.findAll();
        assertEquals(2, cartItems.size());
    }
}
