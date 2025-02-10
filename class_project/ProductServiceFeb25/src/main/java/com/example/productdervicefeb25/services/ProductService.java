package com.example.productdervicefeb25.services;

import com.example.productdervicefeb25.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
}
