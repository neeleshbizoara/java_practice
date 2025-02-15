package com.example.productdervicefeb25.services;

import org.springframework.stereotype.Service;

import com.example.productdervicefeb25.dtos.FakeStoreProductDto;
import com.example.productdervicefeb25.models.Category;
import com.example.productdervicefeb25.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class FakeStoreProductServiceForUpdate implements ProductServiceForUpdate{
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product productDto) {
        FakeStoreProductDto product = new FakeStoreProductDto();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory().getName());
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        String url = "https://fakestoreapi.com/products/" + productId;
        try{

        FakeStoreProductDto fakeProduct = convertProductToFakeStoreProductDto(product);

        // Convert object to JSON
        String requestBody = objectMapper.writeValueAsString(fakeProduct);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Deserialize JSON response
        FakeStoreProductDto updatedFakeProduct = objectMapper.readValue(response.body(), FakeStoreProductDto.class);

        return convertFakeStoreProductDtoToProduct(updatedFakeProduct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
