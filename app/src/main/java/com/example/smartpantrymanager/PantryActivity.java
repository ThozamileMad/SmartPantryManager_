package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryActivity extends AppCompatActivity {
    private RecyclerView foodRecycleView;
    private LinearLayout recipeNavField;
    private FoodDataSource dataSource;
    private List<Food> foodList;
    private FoodAdapter foodAdapter;
    Button addFoodBtn;

    private LinearLayout settingsNavField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        foodRecycleView = findViewById(R.id.ptryItems);

        dataSource = new FoodDataSource(this);

        foodList = dataSource.getAllFood();

        foodAdapter = new FoodAdapter(this, foodList);

        foodRecycleView.setLayoutManager(new LinearLayoutManager(this));
        foodRecycleView.setAdapter(foodAdapter);

        addFoodBtn = findViewById(R.id.ptryAddFoodBtn);
        addFoodBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            this.startActivity(intent);
        });

        recipeNavField = findViewById(R.id.ptryRecipesNavGroup);
        recipeNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            this.startActivity(intent);
        });

        settingsNavField = findViewById(R.id.ptrySettingsNavGroup);
        settingsNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
    }
}
