package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.order.repository.OrderRepository;
import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveOrder() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item = new OrderItem(null, null, null, 2);
        Order order = new Order(null, user, LocalDate.now(), Arrays.asList(item));
        item.setOrder(order);  // Set the order reference in OrderItem
        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getOrderID());
    }

    @Test
    public void testFindOrderById() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item = new OrderItem(null, null, null, 2);
        Order order = new Order(null, user, LocalDate.now(), Arrays.asList(item));
        item.setOrder(order);  // Set the order reference in OrderItem
        Order savedOrder = orderRepository.save(order);
        Optional<Order> retrievedOrder = orderRepository.findById(savedOrder.getOrderID());
        assertTrue(retrievedOrder.isPresent());
        assertEquals(user, retrievedOrder.get().getUser());
    }

    @Test
    public void testFindByUserId() {
        // Create and save a User entity
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        // Create and save Order entities with the saved User
        Order order1 = new Order(null, user, LocalDate.now(), Arrays.asList(new OrderItem()));
        Order order2 = new Order(null, user, LocalDate.now(), Arrays.asList(new OrderItem()));

        orderRepository.save(order1);
        orderRepository.save(order2);

        // Retrieve orders by user ID
        List<Order> orders = orderRepository.findByUser_Id(user.getId());

        assertEquals(2, orders.size());
    }



    @Test
    public void testDeleteOrder() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item = new OrderItem(null, null, null, 2);
        Order order = new Order(null, user, LocalDate.now(), Arrays.asList(item));
        item.setOrder(order);  // Set the order reference in OrderItem
        Order savedOrder = orderRepository.save(order);

        orderRepository.deleteById(savedOrder.getOrderID());
        Optional<Order> deletedOrder = orderRepository.findById(savedOrder.getOrderID());

        assertFalse(deletedOrder.isPresent());
    }

    @Test
    public void testFindAllOrders() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item1 = new OrderItem(null, null, null, 2);
        OrderItem item2 = new OrderItem(null, null, null, 3);
        Order order1 = new Order(null, user, LocalDate.now(), Arrays.asList(item1));
        Order order2 = new Order(null, user, LocalDate.now(), Arrays.asList(item2));
        item1.setOrder(order1);  // Set the order reference in OrderItem
        item2.setOrder(order2);  // Set the order reference in OrderItem

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    public void testOrderItemRelationship() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item1 = new OrderItem(null, null, null, 2);
        OrderItem item2 = new OrderItem(null, null, null, 3);
        Order order = new Order(null, user, LocalDate.now(), Arrays.asList(item1, item2));
        item1.setOrder(order);  // Set the order reference in OrderItem
        item2.setOrder(order);  // Set the order reference in OrderItem

        Order savedOrder = orderRepository.save(order);

        assertEquals(2, savedOrder.getOrderItems().size());
    }

    @Test
    public void testUserRelationship() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        OrderItem item = new OrderItem(null, null, null, 2);
        Order order = new Order(null, user, LocalDate.now(), Arrays.asList(item));
        item.setOrder(order);  // Set the order reference in OrderItem
        Order savedOrder = orderRepository.save(order);

        assertEquals(user, savedOrder.getUser());
    }
}
