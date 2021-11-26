package com.example.demo.dto.models;

import com.example.demo.model.Recipe;

public class RecipeDto {
    private Recipe recipe;
    private long microseconds;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }
}
