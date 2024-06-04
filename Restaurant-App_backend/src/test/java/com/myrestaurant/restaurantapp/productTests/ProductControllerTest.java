package com.myrestaurant.restaurantapp.productTests;

import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.service.ProductService;
import com.myrestaurant.restaurantapp.product.controller.ProductController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Product product2 = new Product(2L, "Pizza Pepperoni", "Pepperoni pizza", 12.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni), 900, "http://example.com/pepperoni.jpg");
        Mockito.when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productController.getAllProducts();
        Assertions.assertEquals(2, products.size());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Pizza Margherita", response.getBody().getName());
        Mockito.verify(productService, Mockito.times(1)).getProductById(1L);
    }

    @Test
    public void testGetProductById_NotFound() {
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(productService, Mockito.times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Product savedProduct = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productService.createProduct(product)).thenReturn(savedProduct);

        Product result = productController.createProduct(product);
        Assertions.assertEquals(1L, result.getProductID());
        Mockito.verify(productService, Mockito.times(1)).createProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Product updatedProduct = new Product(1L, "Pizza Margherita", "Classic pizza updated", 11.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.basil), 850, "http://example.com/pizza_updated.jpg");
        Mockito.when(productService.updateProduct(1L, existingProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(1L, existingProduct);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Pizza Margherita", response.getBody().getName());
        Assertions.assertEquals("Classic pizza updated", response.getBody().getDescription());
        Mockito.verify(productService, Mockito.times(1)).updateProduct(1L, existingProduct);
    }

    @Test
    public void testUpdateProduct_NotFound() {
        Product productDetails = new Product(null, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productService.updateProduct(1L, productDetails)).thenThrow(new RuntimeException("Product not found"));

        ResponseEntity<Product> response = productController.updateProduct(1L, productDetails);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(productService, Mockito.times(1)).updateProduct(1L, productDetails);
    }

    @Test
    public void testDeleteProduct() {
        Mockito.doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<?> response = productController.deleteProduct(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }

    @Test
    public void testDeleteProduct_NotFound() {
        Mockito.doThrow(new RuntimeException("Product not found")).when(productService).deleteProduct(1L);

        ResponseEntity<?> response = productController.deleteProduct(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }
}

