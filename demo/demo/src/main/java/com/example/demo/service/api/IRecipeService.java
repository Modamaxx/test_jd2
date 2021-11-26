package com.example.demo.service.api;

import com.example.demo.dto.filter.RecipeFilter;
import com.example.demo.model.Product;
import com.example.demo.model.Recipe;

import java.util.List;

public interface IRecipeService extends IService<Recipe,Long, RecipeFilter> {

}
