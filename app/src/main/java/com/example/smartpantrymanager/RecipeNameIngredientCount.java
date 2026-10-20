package com.example.smartpantrymanager;

public class RecipeNameIngredientCount {
    private int id;
    private String name;
    private int ingredientCount;
    public RecipeNameIngredientCount(int id, String name, int ingredientCount) {
        this.id = id;
        this.name = name;
        this.ingredientCount = ingredientCount;
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }



    public void setIngredientCount(int ingredientCount) {

        this.ingredientCount = ingredientCount;
    }
}