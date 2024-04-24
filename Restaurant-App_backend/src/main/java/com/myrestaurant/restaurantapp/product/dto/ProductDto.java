package com.myrestaurant.restaurantapp.product.dto;

import java.util.List;

import com.myrestaurant.restaurantapp.product.model.Ingredients;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductDto {
    private Long productID;
    private String name;
    private String description;
    private Double price;
    private List<Ingredients> ingredients;
    private Integer calories;
    private String imageLink;
    
}
