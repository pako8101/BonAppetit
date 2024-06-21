package com.bonappetit.model.entity;

import com.bonappetit.model.entity.enums.CategoryName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {
        this.recipes = new ArrayList<>();
    }

    public Category(CategoryName categoryName, String description) {

        super();
       this.name = categoryName;
       this.description = description;


    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryName getName() {
        return name;
    }

    public Category setName(CategoryName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Category setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}
