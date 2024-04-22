package com.myrestaurant.restaurantapp.product.service;

import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productID) {
        return productRepository.findById(productID);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productID, Product productDetails) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productID));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setIngredients(productDetails.getIngredients());
        product.setImageLink(productDetails.getImageLink());
        return productRepository.save(product);
    }

    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }
}

