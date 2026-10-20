package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeViewActivity extends AppCompatActivity {
    Intent intent;

    int recipeId;
    String recipeName;
    private RecipeDataSource dataSource;


    List<String> instructionList;


    List<RecipeIngredient> ingredientList;

    private RecyclerView ingredientRecyclerView;
    private RecyclerView instructionsRecyclerView;

    private LinearLayout ptryNavField;
    private LinearLayout recipeNavField;
    private LinearLayout settingsNavField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        intent = getIntent();

        recipeId = intent.getIntExtra("recipe_id", -1);
        recipeName = intent.getStringExtra("recipe_name");

        dataSource = new RecipeDataSource(this);

        TextView recipeNameText = findViewById(R.id.rvRecipeNameText);
        recipeNameText.setText(recipeName != null ? recipeName : "Recipe");

        ingredientRecyclerView = findViewById(R.id.rvIngredients);
        instructionsRecyclerView = findViewById(R.id.rvInstructions);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        instructionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ptryNavField = findViewById(R.id.rvPtryNavGroup);
        ptryNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, PantryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        recipeNavField = findViewById(R.id.rvRecipesNavGroup);
        recipeNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        settingsNavField = findViewById(R.id.rvSettingsNavGroup);
        settingsNavField.setOnClickListener(v -> {
            // Intent intent = new Intent(this, SettingsActivity.class);
            // startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecipe();
    }

    private void loadRecipe() {
        ingredientList = dataSource.getRecipeIngredient(recipeId);
        ingredientRecyclerView.setAdapter(new RecipeIngredientAdapter(this, ingredientList));
        instructionsRecyclerView.setAdapter(new RecipeInstructionsAdapter(this, dataSource.getRecipeInstructions(recipeId)));
    }
}