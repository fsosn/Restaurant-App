package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.order.orderItem.repository.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderItem orderItem;

    @BeforeEach
    public void setUp() {
        orderItem = new OrderItem(null, null, null, 2);
    }

    @Test
    public void testSaveOrderItem() {
        OrderItem savedItem = orderItemRepository.save(orderItem);
        assertNotNull(savedItem.getOrderItemID());
    }

    @Test
    public void testFindOrderItemById() {
        OrderItem savedItem = orderItemRepository.save(orderItem);
        Optional<OrderItem> retrievedItem = orderItemRepository.findById(savedItem.getOrderItemID());
        assertTrue(retrievedItem.isPresent());
        assertEquals(2, retrievedItem.get().getQuantity());
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItem savedItem = orderItemRepository.save(orderItem);
        savedItem.setQuantity(5);
        OrderItem updatedItem = orderItemRepository.save(savedItem);

        Optional<OrderItem> retrievedItem = orderItemRepository.findById(updatedItem.getOrderItemID());
        assertTrue(retrievedItem.isPresent());
        assertEquals(5, retrievedItem.get().getQuantity());
    }

    @Test
    public void testDeleteOrderItem() {
        OrderItem savedItem = orderItemRepository.save(orderItem);
        orderItemRepository.deleteById(savedItem.getOrderItemID());

        Optional<OrderItem> retrievedItem = orderItemRepository.findById(savedItem.getOrderItemID());
        assertFalse(retrievedItem.isPresent());
    }

    @Test
    public void testFindAllOrderItems() {
        OrderItem item1 = new OrderItem(null, null, null, 2);
        OrderItem item2 = new OrderItem(null, null, null, 3);
        orderItemRepository.save(item1);
        orderItemRepository.save(item2);

        List<OrderItem> items = orderItemRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    public void testFindByNonExistentId() {
        Optional<OrderItem> retrievedItem = orderItemRepository.findById(999L);
        assertFalse(retrievedItem.isPresent());
    }
}
