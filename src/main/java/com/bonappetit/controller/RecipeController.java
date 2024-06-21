package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {
  private final UserSession userSession;
  private final RecipeService recipeService;

    public RecipeController(UserSession userSession, RecipeService recipeService) {
        this.userSession = userSession;
        this.recipeService = recipeService;
    }

    @ModelAttribute("recipeData")
    public AddRecipeDTO addRecipeDto(){
        return new AddRecipeDTO();
    };

    @GetMapping("/add-recipe")
    public String addRecipe(){
        if (!userSession.isLoggedIn()){
            return "redirect:/";
        }
        return "recipe-add";
    }

    @PostMapping("/add-recipe")
    public String doAddRecipe(@Valid AddRecipeDTO recipeData,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        } else {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("recipeData", recipeData);
                redirectAttributes.addFlashAttribute("org.springframework" +
                                ".validation.BindingResult" +
                                ".recipeData",
                        bindingResult);
                return "redirect:/add-recipe";


            }
         boolean success =   recipeService.add(recipeData);
            if (!success){
                redirectAttributes.addFlashAttribute("recipeData", recipeData);
                return "redirect:/add-recipe";
            }

            return "redirect:/home";

        }

    }
    @PostMapping("/add-to-favourites{recipeId}")
    public String addToFavourites(@PathVariable long recipeId){

        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }

        recipeService.addToFavorites(userSession.getId(), recipeId);


return "redirect:/home";


    }




}
