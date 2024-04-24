package com.myrestaurant.restaurantapp.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String name;

    private String description;

    private Double price;

    private List<Ingredients> ingredients;

    private Integer calories;

    private String imageLink;

    public Product(String name, String description, Double price, Integer calories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
    }

    // Getter methods
    public Long getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public Integer getCalories() {
        return calories;
    }

    public String getImageLink() {
        return imageLink;
    }
}
