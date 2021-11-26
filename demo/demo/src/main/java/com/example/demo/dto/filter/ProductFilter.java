package com.example.demo.dto.filter;

public class ProductFilter extends BaseFilter {
    private String name;

    public ProductFilter(Integer page, Integer size, ESortDirection sortDirection, String name) {
        super(page, size, sortDirection);
        this.name = name;
    }
    public ProductFilter(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
