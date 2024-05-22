package com.myrestaurant.restaurantapp.order.repository;

import com.myrestaurant.restaurantapp.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(Long userID);
}
