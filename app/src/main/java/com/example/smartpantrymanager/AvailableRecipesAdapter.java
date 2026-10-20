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

public class AvailableRecipesAdapter
        extends RecyclerView.Adapter<AvailableRecipesAdapter.AvailableRecipesViewHolder> {

    private final Context context;
    private final List<RecipeNameIngredientCount> availableRecipesList;

    public AvailableRecipesAdapter(Context context, List<RecipeNameIngredientCount> availableRecipesList) {
        this.context = context;
        this.availableRecipesList = availableRecipesList;
    }

    @NonNull
    @Override
    public AvailableRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggested_recipe_item, parent, false);
        return new AvailableRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableRecipesViewHolder holder, int position) {
        RecipeNameIngredientCount recipe = availableRecipesList.get(position);

        holder.recipeName.setText(recipe.getName());
        holder.recipeIngredientCount.setText(recipe.getIngredientCount() + " ingredients");

        holder.recipeContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeViewActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            intent.putExtra("recipe_name", recipe.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return availableRecipesList.size();
    }

    public static class AvailableRecipesViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout recipeContainer;
        TextView recipeName;
        TextView recipeIngredientCount;

        public AvailableRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeContainer = itemView.findViewById(R.id.srItem);
            recipeName = itemView.findViewById(R.id.srRecipeName);
            recipeIngredientCount = itemView.findViewById(R.id.srIngredientCount);
        }
    }
}