package com.example.demo.dto.models;

import com.example.demo.model.Journal;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.model.User;

public class ProductDto {
    private Product product;
    private long microseconds;

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
