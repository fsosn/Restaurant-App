package com.myrestaurant.restaurantapp.orderTests;

import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.order.repository.OrderRepository;
import com.myrestaurant.restaurantapp.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        Order order2 = new Order(2L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> retrievedOrder = orderService.getOrderById(1L);
        assertTrue(retrievedOrder.isPresent());
        assertEquals(1L, retrievedOrder.get().getOrderID());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertEquals(1L, createdOrder.getOrderID());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testGetOrdersByUserId() {
        Order order1 = new Order(1L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        Order order2 = new Order(2L, null, LocalDate.now(), Arrays.asList(new OrderItem()));
        when(orderRepository.findByUser_Id(1L)).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getOrdersByUserId(1L);
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findByUser_Id(1L);
    }

    @Test
    public void testGetOrdersByUserId_NotFound() {
        when(orderRepository.findByUser_Id(1L)).thenReturn(Arrays.asList());

        List<Order> orders = orderService.getOrdersByUserId(1L);
        assertTrue(orders.isEmpty());
        verify(orderRepository, times(1)).findByUser_Id(1L);
    }

    @Test
    public void testCreateOrderWithInvalidData() {
        Order order = new Order();
        when(orderRepository.save(any(Order.class))).thenThrow(new IllegalArgumentException("Invalid order data"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(order);
        });

        assertEquals("Invalid order data", exception.getMessage());
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}

