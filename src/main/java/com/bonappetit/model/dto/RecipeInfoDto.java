package com.bonappetit.model.dto;

import com.bonappetit.model.entity.Recipe;

public class RecipeInfoDto {
    private long id;
    private String name;
    private String ingredients;

    public RecipeInfoDto(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public RecipeInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeInfoDto setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
