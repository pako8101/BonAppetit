package com.bonappetit.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

@Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
private String ingredients;
    @ManyToOne(optional = false)
private Category category;
    @ManyToOne(optional = false)
    private User addedBy;

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public Recipe setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Recipe setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Recipe setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
