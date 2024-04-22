package com.myrestaurant.restaurantapp.shoppingCart.controller;

import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{cartID}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long cartID) {
        return shoppingCartService.getCartById(cartID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ShoppingCart createCart(@RequestBody ShoppingCart cart) {
        return shoppingCartService.createCart(cart);
    }

    @DeleteMapping("/{cartID}")
    public ResponseEntity<?> deleteCart(@PathVariable Long cartID) {
        shoppingCartService.deleteCart(cartID);
        return ResponseEntity.ok().build();
    }
}

