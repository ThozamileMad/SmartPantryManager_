package com.example.smartpantrymanager;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private List<String> instructions;

    public Recipe(int id,
            String name,
            List<String> instructions
    ) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    // SETTERS

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void addInstruction(String instructions) {
        for (String i : instructions.split("\\|")) {
            getInstructions().add(i);
        }
    }
}