package com.myrestaurant.restaurantapp.productTests;

import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductCreation() {
        Product product = new Product(
                1L,
                "Pizza Margherita",
                "Classic pizza with tomatoes and cheese",
                10.99,
                Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese),
                800,
                "http://example.com/pizza.jpg"
        );

        Assertions.assertEquals(1L, product.getProductID());
        Assertions.assertEquals("Pizza Margherita", product.getName());
        Assertions.assertEquals("Classic pizza with tomatoes and cheese", product.getDescription());
        Assertions.assertEquals(10.99, product.getPrice());
        Assertions.assertEquals(Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese), product.getIngredients());
        Assertions.assertEquals(800, product.getCalories());
        Assertions.assertEquals("http://example.com/pizza.jpg", product.getImageLink());
    }

    @Test
    public void testSetProductName() {
        Product product = new Product();
        product.setName("Pizza Pepperoni");
        Assertions.assertEquals("Pizza Pepperoni", product.getName());
    }

    @Test
    public void testSetProductDescription() {
        Product product = new Product();
        product.setDescription("Spicy pepperoni pizza");
        Assertions.assertEquals("Spicy pepperoni pizza", product.getDescription());
    }

    @Test
    public void testSetProductPrice() {
        Product product = new Product();
        product.setPrice(12.99);
        Assertions.assertEquals(12.99, product.getPrice());
    }

    @Test
    public void testSetProductIngredients() {
        Product product = new Product();
        product.setIngredients(Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni));
        Assertions.assertEquals(Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni), product.getIngredients());
    }

    @Test
    public void testSetProductCalories() {
        Product product = new Product();
        product.setCalories(900);
        Assertions.assertEquals(900, product.getCalories());
    }

    @Test
    public void testSetProductImageLink() {
        Product product = new Product();
        product.setImageLink("http://example.com/pepperoni.jpg");
        Assertions.assertEquals("http://example.com/pepperoni.jpg", product.getImageLink());
    }

    @Test
    public void testEmptyIngredients() {
        Product product = new Product();
        product.setIngredients(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), product.getIngredients());
    }

    @Test
    public void testNullIngredients() {
        Product product = new Product();
        product.setIngredients(null);
        Assertions.assertNull(product.getIngredients());
    }

    @Test
    public void testFullProductUpdate() {
        Product product = new Product();
        product.setProductID(2L);
        product.setName("Updated Pizza");
        product.setDescription("Updated Description");
        product.setPrice(15.99);
        product.setIngredients(Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni));
        product.setCalories(1000);
        product.setImageLink("http://example.com/updated_pizza.jpg");

        Assertions.assertEquals(2L, product.getProductID());
        Assertions.assertEquals("Updated Pizza", product.getName());
        Assertions.assertEquals("Updated Description", product.getDescription());
        Assertions.assertEquals(15.99, product.getPrice());
        Assertions.assertEquals(Arrays.asList(Ingredients.flour, Ingredients.tomatoes, Ingredients.cheese, Ingredients.pepperoni), product.getIngredients());
        Assertions.assertEquals(1000, product.getCalories());
        Assertions.assertEquals("http://example.com/updated_pizza.jpg", product.getImageLink());
    }

    @Test
    public void testDefaultConstructor() {
        Product product = new Product();
        Assertions.assertNull(product.getProductID());
        Assertions.assertNull(product.getName());
        Assertions.assertNull(product.getDescription());
        Assertions.assertNull(product.getPrice());
        Assertions.assertNull(product.getIngredients());
        Assertions.assertNull(product.getCalories());
        Assertions.assertNull(product.getImageLink());
    }
}
