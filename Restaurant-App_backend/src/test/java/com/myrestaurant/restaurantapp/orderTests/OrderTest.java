package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.user.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrderCreation() {
        User user = new User();
        OrderItem item = new OrderItem(1L, null, null, 2);
        Order order = new Order(
                1L,
                user,
                LocalDate.now(),
                Arrays.asList(item)
        );

        assertEquals(1L, order.getOrderID());
        assertEquals(user, order.getUser());
        assertEquals(LocalDate.now(), order.getDate());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(1L, order.getOrderItems().get(0).getOrderItemID());
    }

    @Test
    public void testSetOrderDate() {
        Order order = new Order();
        LocalDate date = LocalDate.now();
        order.setDate(date);
        assertEquals(date, order.getDate());
    }

    @Test
    public void testSetUser() {
        Order order = new Order();
        User user = new User();
        order.setUser(user);
        assertEquals(user, order.getUser());
    }

    @Test
    public void testSetOrderItems() {
        Order order = new Order();
        OrderItem item1 = new OrderItem(1L, null, null, 2);
        OrderItem item2 = new OrderItem(2L, null, null, 3);
        List<OrderItem> items = Arrays.asList(item1, item2);
        order.setOrderItems(items);
        assertEquals(items, order.getOrderItems());
    }
}
