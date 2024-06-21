package com.bonappetit.controller;

import com.bonappetit.model.dto.RecipeInfoDto;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.service.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
private final UserSession userSession;
private final UserService userService;
private final RecipeService recipeService;

    public HomeController(UserSession userSession, UserService userService, RecipeService recipeService) {
        this.userSession = userSession;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String nonLoggedIndex(){
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }
    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model){
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        Map<CategoryName, List<Recipe>> allRecipes
                = recipeService.findAllByCategory();
        List<RecipeInfoDto>favorites =
                userService.findFavourites(userSession.getId())
                        .stream()
                        .map(RecipeInfoDto::new)
                        .toList();
//
List<RecipeInfoDto>cocktails =
        allRecipes.get(CategoryName.COCKTAIL).
                stream()
                        .map(RecipeInfoDto::new)
                        .toList();
        model.addAttribute("cocktails",cocktails);
        List<RecipeInfoDto>desserts =
                allRecipes.get(CategoryName.DESSERT).
                        stream()
                        .map(RecipeInfoDto::new)
                        .toList();
        model.addAttribute("desserts"
                ,desserts);

        List<RecipeInfoDto>mainDishes =
                allRecipes.get(CategoryName.MAIN_DISH).
                        stream()
                        .map(RecipeInfoDto::new)
                        .toList();
        model.addAttribute("mainDishes"
                ,mainDishes);

        model.addAttribute("favoritesData",favorites);






        return "home";
    }

}
