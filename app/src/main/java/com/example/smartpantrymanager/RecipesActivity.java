package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private RecyclerView availableRecyclerView;
    private RecyclerView unavailableRecyclerView;

    private TextView emptyMessage;

    private LinearLayout pantryNavField;

    private RecipeDataSource dataSource;
    private LinearLayout settingsNavField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        availableRecyclerView = findViewById(R.id.srSuggestedRecipesItems);
        unavailableRecyclerView = findViewById(R.id.srUnavailableRecipesItems);
        emptyMessage = findViewById(R.id.srEmptyMessage);

        availableRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        unavailableRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataSource = new RecipeDataSource(this);

        pantryNavField = findViewById(R.id.srNavGroup);
        pantryNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, PantryActivity.class);
            startActivity(intent);
        });

        settingsNavField = findViewById(R.id.srSettingsNavGroup);
        settingsNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecipes();
    }

    private void loadRecipes() {
        List<RecipeNameIngredientCount> availableList = dataSource.getRecipeNameAndIngredientCount();
        List<RecipeWithMissingIngredients> unavailableList = dataSource.getRecipeWithMissingIngredients();
        availableRecyclerView.setAdapter(new AvailableRecipesAdapter(this, availableList));
        unavailableRecyclerView.setAdapter(new UnavailableRecipesAdapter(this, unavailableList));
        emptyMessage.setVisibility(availableList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}