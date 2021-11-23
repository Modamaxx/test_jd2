package com.example.demo.controller.restController;

import com.example.demo.dto.filter.RecipeFilter;
import com.example.demo.dto.models.RecipeDto;
import com.example.demo.dto.models.WeightDto;
import com.example.demo.model.Recipe;
import com.example.demo.dto.filter.ESortDirection;
import com.example.demo.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/recipe")
public class RecipeRestController {
    private final IRecipeService recipeService;

    public RecipeRestController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    public ResponseEntity<Page<Recipe>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "name", required = false) String name) {

        RecipeFilter pageableFilter = new RecipeFilter(page, size, name, ESortDirection.DESC);
        Page<Recipe> recipes = recipeService.getAll(pageableFilter);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> get(@PathVariable Long id) {
        Recipe recipe = recipeService.get(id);
        long microseconds = recipe.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setRecipe(recipe);
        recipeDto.setMicroseconds(microseconds);
        return new ResponseEntity<>(recipeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@RequestBody Recipe updateRecipe, @PathVariable Long id,
                                    @PathVariable("dt_update") long dtUpdate) {
        recipeService.update(updateRecipe, id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable("dt_update") long dtUpdate) {
        recipeService.delete(id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
