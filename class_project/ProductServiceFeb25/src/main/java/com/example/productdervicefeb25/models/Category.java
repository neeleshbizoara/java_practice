package com.example.productdervicefeb25.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="categories")
public class Category extends BaseModel{
//    private Long Id;
    private String name;
}
