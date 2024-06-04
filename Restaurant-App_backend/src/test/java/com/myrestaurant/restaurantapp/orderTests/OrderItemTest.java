package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.product.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    @Test
    public void testOrderItemCreation() {
        Order order = new Order();
        Product product = new Product();
        OrderItem item = new OrderItem(1L, order, product, 2);

        assertEquals(1L, item.getOrderItemID());
        assertEquals(order, item.getOrder());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        OrderItem item = new OrderItem();
        item.setQuantity(5);
        assertEquals(5, item.getQuantity());
    }

    @Test
    public void testSetOrder() {
        Order order = new Order();
        OrderItem item = new OrderItem();
        item.setOrder(order);
        assertEquals(order, item.getOrder());
    }

    @Test
    public void testSetProduct() {
        Product product = new Product();
        OrderItem item = new OrderItem();
        item.setProduct(product);
        assertEquals(product, item.getProduct());
    }

    @Test
    public void testSetOrderItemID() {
        OrderItem item = new OrderItem();
        item.setOrderItemID(1L);
        assertEquals(1L, item.getOrderItemID());
    }

    @Test
    public void testUpdateOrderItem() {
        Order order = new Order();
        Product product = new Product();
        OrderItem item = new OrderItem(1L, order, product, 2);

        item.setQuantity(3);
        assertEquals(3, item.getQuantity());

        Order newOrder = new Order();
        item.setOrder(newOrder);
        assertEquals(newOrder, item.getOrder());

        Product newProduct = new Product();
        item.setProduct(newProduct);
        assertEquals(newProduct, item.getProduct());
    }

    @Test
    public void testDeleteOrderItem() {
        Order order = new Order();
        Product product = new Product();
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem item = new OrderItem(1L, order, product, 2);
        orderItems.add(item);

        assertTrue(orderItems.contains(item));
        orderItems.remove(item);
        assertFalse(orderItems.contains(item));
    }

    @Test
    public void testGetOrderItemDetails() {
        Order order = new Order();
        Product product = new Product();
        OrderItem item = new OrderItem(1L, order, product, 2);

        assertEquals(1L, item.getOrderItemID());
        assertEquals(order, item.getOrder());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

}
