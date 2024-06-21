package com.bonappetit.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Column(unique = true,nullable = false)
private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;
    @OneToMany(mappedBy = "addedBy")
    private List<Recipe>addedRecipes;
    @ManyToMany()
    private List<Recipe>favouriteRecipes;

    public User() {
        this.favouriteRecipes = new ArrayList<>();
        this.addedRecipes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public User setAddedRecipes(List<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
        return this;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public User setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
        return this;
    }

    public void addFavourite(Recipe recipe) {
        this.favouriteRecipes.add(recipe);
    }
}
