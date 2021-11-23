package com.example.demo.dto.filter;

public class RecipeFilter extends BaseFilter {
    private String name;

    public RecipeFilter(Integer page, Integer size,  String name,ESortDirection sortDirection) {
        super(page, size, sortDirection);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
