package com.myrestaurant.restaurantapp.order.orderItem.repository;

import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
