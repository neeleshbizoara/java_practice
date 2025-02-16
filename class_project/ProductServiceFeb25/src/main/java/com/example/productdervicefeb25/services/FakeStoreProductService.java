package com.example.productdervicefeb25.services;

import com.example.productdervicefeb25.dtos.FakeStoreProductDto;
import com.example.productdervicefeb25.exceptions.ProductNotFoundException;
import com.example.productdervicefeb25.models.Category;
import com.example.productdervicefeb25.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    // This service implementation uses FakeStore to fetch product from FakeStore
    private RestTemplate obj;

    public FakeStoreProductService(RestTemplate restTemplate){

        this.obj = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct (FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

//        Category category = new Category();
//        category.setName(fakeStoreProductDto.getCategory());
//        product.setCategory(category);

        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto (Product productDto){
        FakeStoreProductDto product = new FakeStoreProductDto();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());

//        Category category = new Category();
//        category.setName(fakeStoreProductDto.getCategory());
//        product.setCategory(category);

        product.setCategory(productDto.getCategory().getName());
//        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }
    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        // Make a API call to FakeStore and get the product with the given Id.
        FakeStoreProductDto fakeStoreProduct = obj.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);

        // Convert FakeStoreProductDto object into Product object
        if(fakeStoreProduct != null) {
            throw new ProductNotFoundException("Product with id: " + productId + " does not exist.");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProduct);
//        throw new RuntimeException("Something went wrong.");
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos =
                obj.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Product createdProduct = obj.postForObject("https://fakestoreapi.com/products", product, Product.class);
        return createdProduct;
    }

//    @Override
//    public Product updateProduct(Long productId, Product product) {
//        FakeStoreProductDto fakeproduct = convertProductToFakeStoreProductDto(product);
//        FakeStoreProductDto updateProduct = obj.patchForObject("https://fakestoreapi.com/products/" + productId, fakeproduct, FakeStoreProductDto.class);
//        return convertFakeStoreProductDtoToProduct(updateProduct);
////        return updateProduct;
//    }
}
