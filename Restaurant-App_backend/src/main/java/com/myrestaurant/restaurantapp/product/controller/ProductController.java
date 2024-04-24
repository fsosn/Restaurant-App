package com.myrestaurant.restaurantapp.product.controller;

import com.myrestaurant.restaurantapp.product.dto.ProductDto;
import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("$api.products.base")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("${api.products.get.all}")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("${api.products.id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productID) {
        ProductDto product = productService.getProductDtoById(productID);
        return ResponseEntity.ok(product);
    }

    @PostMapping("{$api.products.create}")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            Product createdProduct = productService.createProduct(productDto);
            return ResponseEntity.ok(createdProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("${api.products.put}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProductDto = productService.updateProduct(productId, productDto);
            return ResponseEntity.ok(updatedProductDto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("${api.products.delete}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productID) {
        productService.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
