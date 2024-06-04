package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.controller.OrderController;
import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        Order order2 = new Order(2L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderController.getAllOrders();
        assertEquals(2, orders.size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = orderController.getOrderById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getOrderID());
        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    public void testGetOrderById_NotFound() {
        when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Order> response = orderController.getOrderById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        Order createdOrder = orderController.createOrder(order);
        assertEquals(1L, createdOrder.getOrderID());
        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    public void testCreateOrder_NullInput() {
        Order createdOrder = orderController.createOrder(null);
        assertEquals(null, createdOrder);
        verify(orderService, times(0)).createOrder(any(Order.class));
    }

    @Test
    public void testGetOrdersByUserId() {
        Order order1 = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        Order order2 = new Order(2L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderService.getOrdersByUserId(1L)).thenReturn(Arrays.asList(order1, order2));

        ResponseEntity<List<Order>> response = orderController.getOrdersByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(orderService, times(1)).getOrdersByUserId(1L);
    }

    @Test
    public void testGetOrdersByUserId_NotFound() {
        when(orderService.getOrdersByUserId(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Order>> response = orderController.getOrdersByUserId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(orderService, times(1)).getOrdersByUserId(1L);
    }

    @Test
    public void testGetOrdersByUserId_InvalidId() {
        ResponseEntity<List<Order>> response = orderController.getOrdersByUserId(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(orderService, times(0)).getOrdersByUserId(null);
    }
}
