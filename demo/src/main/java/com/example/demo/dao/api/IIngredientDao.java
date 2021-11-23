package com.example.demo.dao.api;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientDao extends JpaRepository<Ingredient,Long> {
}
