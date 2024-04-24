package com.myrestaurant.restaurantapp.product.service;

import com.myrestaurant.restaurantapp.product.dto.ProductDto;
//import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(this::mapToProductDto).collect(Collectors.toList());
    }

    public ProductDto getProductDtoById(Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found for ID: " + productId);
        }
        return mapToProductDto(productOptional.get());
    }

    public Product createProduct(ProductDto productDto) {
        String name = productDto.getName();
        String description = productDto.getDescription();
        Double price = productDto.getPrice();
        //List<Ingredients> ingredients = productDto.getIngredients();
        Integer calories = productDto.getCalories();
        //String imageLink = productDto.getImageLink();
        Product product = new Product(name, description, price, calories);
        return productRepository.save(product);
    }

    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setIngredients(productDto.getIngredients());
        product.setImageLink(productDto.getImageLink());
    
        product = productRepository.save(product);
    
        return mapToProductDto(product);
    }
    

    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getProductID(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getIngredients(),
                product.getCalories(),
                product.getImageLink()
        );
    }
}

