package com.myrestaurant.restaurantapp.shoppingCart.cartItem.repository;

import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

