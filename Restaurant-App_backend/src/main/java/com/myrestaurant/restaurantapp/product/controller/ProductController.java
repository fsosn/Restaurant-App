package com.myrestaurant.restaurantapp.product.controller;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productID) {
        return productService.getProductById(productID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productID, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(productID, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productID) {
        productService.deleteProduct(productID);
        return ResponseEntity.ok().build();
    }
}
