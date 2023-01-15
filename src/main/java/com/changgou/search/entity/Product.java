package com.changgou.search.entity;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String name;
    private String price;
    private int number;
    private int pageNum;

    List<Product> list;

    public Product(String name, String price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Product() {
    }
}
