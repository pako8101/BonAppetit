package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService  implements CommandLineRunner {
private  Map<CategoryName,String>categoryDescription
        =Map.of(
                CategoryName.MAIN_DISH,"Heart of the meal, " +
                "substantial and satisfying; main dish delights taste buds."
        ,CategoryName.COCKTAIL,"Sweet finale, indulgent and delightful; " +
                "dessert crowns the dining experience with joy",
        CategoryName.DESSERT,"Sip of sophistication, cocktails blend flavors," +
                " creating a spirited symphony in every glass"
);
    private final CategoryRepository categoryRepository;

    public InitService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long count= categoryRepository.count();

        if (count>0){
            return;
        }
        List<Category> toInsert =
                Arrays.stream(CategoryName.values()).map(
                        cat -> new Category(cat,categoryDescription.get(cat))
                ).toList();

        categoryRepository.saveAll(toInsert);
//        for (CategoryName categoryName : categoryDescription.keySet()){
//
//            Category category = new Category(categoryName
//                    ,categoryDescription.get(categoryName));
//
//            categoryRepository.save(category);
//        }

    }
}
