package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnavailableRecipesAdapter
        extends RecyclerView.Adapter<UnavailableRecipesAdapter.UnavailableRecipesViewHolder> {

    private final Context context;
    private final List<RecipeWithMissingIngredients> unavailableRecipesList;

    public UnavailableRecipesAdapter(Context context, List<RecipeWithMissingIngredients> unavailableRecipesList) {
        this.context = context;
        this.unavailableRecipesList = unavailableRecipesList;
    }

    @NonNull
    @Override
    public UnavailableRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        return new UnavailableRecipesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggested_recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UnavailableRecipesViewHolder holder, int position) {
        RecipeWithMissingIngredients recipe = unavailableRecipesList.get(position);

        holder.recipeName.setText(recipe.getName());

        List<String> missing = recipe.getMissingIngredients();
        holder.missingIngredients.setText("Missing: " + String.join(", ", missing));
        holder.recipeContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeViewActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            intent.putExtra("recipe_name", recipe.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return unavailableRecipesList.size();
    }

    public static class UnavailableRecipesViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout recipeContainer;
        TextView recipeName;
        TextView missingIngredients;
        public UnavailableRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeContainer = itemView.findViewById(R.id.srItem);
            recipeName = itemView.findViewById(R.id.srRecipeName);
            missingIngredients = itemView.findViewById(R.id.srIngredientCount);
        }
    }
}