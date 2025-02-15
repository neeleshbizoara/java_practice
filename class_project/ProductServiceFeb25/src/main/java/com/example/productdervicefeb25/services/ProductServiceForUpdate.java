package com.example.productdervicefeb25.services;

import com.example.productdervicefeb25.models.Product;

public interface ProductServiceForUpdate {
    Product updateProduct(Long productId, Product product);
}
