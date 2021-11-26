package com.example.demo.service.validator;

import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.model.Weight;
import com.example.demo.service.RecipeService;
import com.example.demo.service.api.IRecipeService;
import com.example.demo.service.api.IWeightService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class RecipeValidatorService {
    private final IRecipeService recipeService;

    public RecipeValidatorService(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Before("execution(* com.example.demo.service.RecipeService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ActiveFilter activeFilter = (ActiveFilter) args[0];

        if (activeFilter.getPage() == null || activeFilter.getSize() == null) {
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }

    }

    @Before("execution(* com.example.demo.service.RecipeService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Recipe recipe = (Recipe) args[0];

        validBasicParameters(recipe);
    }

    @Before("execution(* com.example.demo.service.RecipeService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Recipe recipeUpdate = (Recipe) args[0];
        Long idRecipe = (Long) args[1];
        long dtUpdate = (long) args[2];

        Recipe recipe = recipeService.get(idRecipe);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }
        long dtBd = recipe.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        validBasicParameters(recipeUpdate);

    }

    @Before("execution(* com.example.demo.service.RecipeService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idRecipe = (Long) args[0];
        long dtUpdate = (long) args[1];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Recipe recipe = recipeService.get(idRecipe);
        long dtBd = recipe.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }

    public void validBasicParameters(Recipe recipe) {
        if (recipe.getName() == null) {
            throw new IllegalArgumentException("Имя указано неверно");
        }

        for (Ingredient i : recipe.getIngredient()) {
            if (i.getMass() <= 0) {
                throw new IllegalArgumentException("Вес указан неверно");
            }
            if (i.getProduct() == null) {
                throw new IllegalArgumentException("Product не указан");
            }
        }
    }
}
