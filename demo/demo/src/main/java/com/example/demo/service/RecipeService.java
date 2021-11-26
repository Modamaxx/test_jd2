package com.example.demo.service;

import com.example.demo.dao.api.IIngredientDao;
import com.example.demo.dao.api.IRecipeDao;
import com.example.demo.dto.filter.RecipeFilter;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.User;
import com.example.demo.service.api.IRecipeService;
import com.example.demo.service.api.IUserService;
import com.example.security.UserHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecipeService implements IRecipeService {
    private final IRecipeDao recipeDao;
    private final IIngredientDao ingredientDao;
    private final UserHolder userHolder;
    private final IUserService userService;

    public RecipeService(IRecipeDao recipeDao, IIngredientDao ingredientDao, UserHolder userHolder, IUserService userService) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public void save(Recipe recipe) {
        LocalDateTime time = LocalDateTime.now();
        recipe.setDtCreate(time);
        recipe.setDtUpdate(time);

        String login = userHolder.getAuthentication().getName();
        User user = userService.findByLogin(login);
        recipe.setUser(user);

        for (Ingredient i : recipe.getIngredient()) { //чтобы было на что ссылаться базе данныхе при сохранение recipe
            ingredientDao.save(i);
        }
        recipeDao.save(recipe);
    }

    @Override
    public void delete(Long id, long dtUpdate) {
        Recipe recipe = recipeDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Блюда по данному id не существует"));
        recipeDao.deleteById(id);

        for (Ingredient i : recipe.getIngredient()) { //чтобы подчистить за собой ингридиенты
            ingredientDao.deleteById(i.getId());
        }

    }

    @Override
    public Recipe get(Long id) {
        return recipeDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Блюда по данному id не существует"));
    }

    @Override
    public void update(Recipe updateRecipe, Long idRecipe, long dtUpdate) {
        Recipe recipe = get(idRecipe);

        updateRecipe.setId(idRecipe);
        updateRecipe.setDtCreate(recipe.getDtCreate());
        updateRecipe.setDtUpdate(LocalDateTime.now());

        for (Ingredient i : recipe.getIngredient()) {
            ingredientDao.save(i);
        }

        recipeDao.save(updateRecipe);
    }

    @Override
    public Page<Recipe> getAll(RecipeFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        if (filter.getName() != null) {
            return recipeDao.findProductByName(filter.getName(), pageable);
        }
        return recipeDao.findAll(pageable);
    }

}
