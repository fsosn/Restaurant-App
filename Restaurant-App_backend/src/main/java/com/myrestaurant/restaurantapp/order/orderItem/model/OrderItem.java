package com.myrestaurant.restaurantapp.order.orderItem.model;

import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemID;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;

    private Integer quantity;
}

