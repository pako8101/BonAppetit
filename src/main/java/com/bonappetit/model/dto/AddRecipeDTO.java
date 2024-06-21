package com.bonappetit.model.dto;

import com.bonappetit.model.entity.enums.CategoryName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddRecipeDTO {
    @NotNull
    @Size(min = 2, max = 40)
    private String name;
    @NotNull
    @Size(min = 2, max = 150)
    private String ingredients;

    @NotNull
    private CategoryName category;

    public AddRecipeDTO() {
    }

    public String getName() {
        return name;
    }

    public AddRecipeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public AddRecipeDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public CategoryName getCategory() {
        return category;
    }

    public AddRecipeDTO setCategory(CategoryName category) {
        this.category = category;
        return this;
    }
}
