package com.myrestaurant.restaurantapp.productTests;

import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import com.myrestaurant.restaurantapp.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Product product2 = new Product(2L, "Pizza Pepperoni", "Pepperoni pizza", 12.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni), 900, "http://example.com/pepperoni.jpg");
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        Assertions.assertEquals(2, products.size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.getProductById(1L);
        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals("Pizza Margherita", retrievedProduct.get().getName());
        Mockito.verify(productRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productRepository.save(product)).thenReturn(new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg"));

        Product createdProduct = productService.createProduct(product);
        Assertions.assertNotNull(createdProduct.getProductID());
        Assertions.assertEquals("Pizza Margherita", createdProduct.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Product updatedProduct = new Product(1L, "Updated Pizza Margherita", "Updated pizza", 11.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 850, "http://example.com/updated_pizza.jpg");
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(ArgumentMatchers.any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.updateProduct(1L, updatedProduct);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Updated Pizza Margherita", result.getName());
        Mockito.verify(productRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Pizza Margherita", "Classic pizza", 10.99, Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), 800, "http://example.com/pizza.jpg");
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }

}

