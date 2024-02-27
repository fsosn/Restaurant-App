package com.myrestaurant.restaurantapp.shoppingCart.cartItem.model;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemID;

    @ManyToOne
    @JoinColumn(name = "cartID", referencedColumnName = "cartID")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;

    private Integer quantity;
}

