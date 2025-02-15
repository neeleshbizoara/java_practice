package com.example.productdervicefeb25.services;

import com.example.productdervicefeb25.exceptions.ProductNotFoundException;
import com.example.productdervicefeb25.models.Product;

import java.util.List;

public class SelfProductService implements ProductService{
    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
