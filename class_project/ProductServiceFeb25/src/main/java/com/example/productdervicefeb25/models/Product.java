package com.example.productdervicefeb25.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product extends BaseModel {
    private Long id;
//    private Date createdAt;
//    private Date lastModifiedAt;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private Category category;
}
