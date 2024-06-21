package com.bonappetit.service.impl;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
private final UserRepository userRepository;
private final CategoryRepository categoryRepository;
private final RecipeRepository recipeRepository;
private final UserSession userSession;

    public RecipeServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.userSession = userSession;
    }


    @Override
    public boolean add(AddRecipeDTO recipeData) {
if (!userSession.isLoggedIn()){
    return false;
}

       Optional<User> userById = userRepository.
                findById(userSession.getId());
if (userById.isEmpty()){
    return false;
}
        Optional<Category> categoryName = categoryRepository
                .findByName(recipeData.getCategory());
        if (categoryName.isEmpty()){
            return false;
        }
        Recipe recipe = new Recipe();
       recipe.setName(recipeData.getName())
               .setIngredients(recipeData.getIngredients())
               .setCategory(categoryName.get())
               .setAddedBy(userById.get());



        recipeRepository.save(recipe);



return true;


    }

    @Override
    public Map<CategoryName, List<Recipe>> findAllByCategory() {
        Map<CategoryName, List<Recipe>> result = new HashMap<>();

       List<Category> allCategories =  categoryRepository.findAll();
//       for (Category category : allCategories){
//           List<Recipe> recipes =
//                   recipeRepository.findAllByCategory(category);
//           result.put(category.getName(),recipes);
//       }

allCategories.forEach(category -> {
    List<Recipe> recipes =
            recipeRepository.findAllByCategory(category);
    result.put(category.getName(),recipes);
});



        return result;
    }

    @Override
    @Transactional
    public void addToFavorites(long id, long recipeId) {
        Optional<User>userById = userRepository.findById(id);
        if (userById.isEmpty()){
            return;
        }
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isEmpty()){
            return;
        }
        userById.get().addFavourite(optionalRecipe.get());

        userRepository.save(userById.get());
//        if (optionalRecipe.isPresent()) {
//            Optional<User> user = userRepository.findByUsername(userSession.getUsername());
//            Recipe recipe = optionalRecipe.get();
//
//            recipe.setAddedBy(user.get());
//
//
//            recipeRepository.save(recipe);
//        }
    }


}
