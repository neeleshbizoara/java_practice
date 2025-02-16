package com.example.productdervicefeb25.controllers;

import com.example.productdervicefeb25.exceptions.ProductNotFoundException;
import com.example.productdervicefeb25.models.Product;
import com.example.productdervicefeb25.services.ProductService;
import com.example.productdervicefeb25.services.ProductServiceForUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductServiceForUpdate productServiceForUpdate;

    public ProductController(ProductService productService, ProductServiceForUpdate productServiceForUpdate){
        this.productService = productService;
        this.productServiceForUpdate = productServiceForUpdate;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
//        Product product = productService.getProductById(id);
//        return new ResponseEntity<>(
//                product,
//                // HttpStatus.SERVICE_UNAVAILABLE
//                HttpStatus.OK
//        );

        ResponseEntity<Product> responseEntity = null;
        try{
            Product product = productService.getProductById(id);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        } catch(ProductNotFoundException exc) {
            System.out.println(exc.getMessage());
            responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }*/

    /*public Product getProductById(@PathVariable("id") Long id){
        System.out.println(id);
//        System.out.println(productService.getProductById(id));
        return productService.getProductById(id);
    }*/

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        ResponseEntity<Product> responseEntity = null;
        responseEntity = new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        ResponseEntity<Product> responseEntity = null;
//        responseEntity = new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
        responseEntity = new ResponseEntity<>(productServiceForUpdate.updateProduct(id, product), HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handelProductNotFoundException(ProductNotFoundException e) {
        System.out.println("Controller Exception Handler called");
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
