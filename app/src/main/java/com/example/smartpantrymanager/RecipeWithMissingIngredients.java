package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class RecipeWithMissingIngredients {
    private int id;
    private String name;
    private List<String> missingIngredients = new ArrayList<>();

    // CONSTRUCTOR
    public RecipeWithMissingIngredients(
            int id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMissingIngredients() {
        return missingIngredients;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMissingIngredient(String missingIngredient) {
        missingIngredients.add(missingIngredient);
    }
}
