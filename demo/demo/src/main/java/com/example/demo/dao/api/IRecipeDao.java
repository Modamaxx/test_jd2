package com.example.demo.dao.api;

import com.example.demo.model.Product;
import com.example.demo.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeDao extends JpaRepository<Recipe,Long> {

    Page<Recipe> findProductByName(String name, Pageable pageable);
}
