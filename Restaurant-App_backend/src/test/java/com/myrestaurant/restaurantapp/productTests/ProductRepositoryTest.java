package com.myrestaurant.restaurantapp.productTests;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product savedProduct = productRepository.save(product);
        Assertions.assertNotNull(savedProduct.getProductID());
    }

    @Test
    public void testFindProductById() {
        Product product = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product savedProduct = productRepository.save(product);
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getProductID());
        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals("Pizza Margherita", retrievedProduct.get().getName());
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product product2 = new Product(
                null,
                "Pizza Pepperoni",
                "Pizza with pepperoni and cheese",
                12.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni),
                900,
                "http://example.com/pepperoni.jpg"
        );
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product savedProduct = productRepository.save(product);

        savedProduct.setName("Updated Pizza Margherita");
        savedProduct.setDescription("Updated description with more cheese");
        Product updatedProduct = productRepository.save(savedProduct);

        Optional<Product> retrievedProduct = productRepository.findById(updatedProduct.getProductID());
        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals("Updated Pizza Margherita", retrievedProduct.get().getName());
        Assertions.assertEquals("Updated description with more cheese", retrievedProduct.get().getDescription());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct);
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getProductID());
        Assertions.assertFalse(retrievedProduct.isPresent());
    }

    @Test
    public void testFindProductsByName() {
        Product product1 = new Product(
                null,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );
        Product product2 = new Product(
                null,
                "Pizza Pepperoni",
                "Pizza with pepperoni and cheese",
                12.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni),
                900,
                "http://example.com/pepperoni.jpg"
        );
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> margheritaProducts = productRepository.findByName("Pizza Margherita");
        Assertions.assertEquals(1, margheritaProducts.size());
        Assertions.assertEquals("Pizza Margherita", margheritaProducts.get(0).getName());
    }
}

