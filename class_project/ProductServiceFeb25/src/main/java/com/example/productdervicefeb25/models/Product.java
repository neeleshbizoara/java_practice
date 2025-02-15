package com.example.productdervicefeb25.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name="products")
public class Product extends BaseModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Date createdAt;
//    private Date lastModifiedAt;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    @ManyToOne
    private Category category;
}
