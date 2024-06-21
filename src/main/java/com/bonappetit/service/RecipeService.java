package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.enums.CategoryName;

import java.util.List;
import java.util.Map;

public interface RecipeService {
    boolean add(AddRecipeDTO recipeData);

    public Map<CategoryName, List<Recipe>> findAllByCategory();

    void addToFavorites(long id,long recipeId);
//    public List<Recipe> findFavourites(Long userId);
}
