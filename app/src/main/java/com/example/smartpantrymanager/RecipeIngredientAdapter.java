package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.RecipeIngredientViewHolder> {
    private Context context;
    private List<RecipeIngredient> ingredientList;

    public RecipeIngredientAdapter(Context context, List<RecipeIngredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override
    public RecipeIngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(
                                parent.getContext())
                        .inflate(
                                R.layout.recipe_ingredient_item,

                                parent,
                                false);

        return new RecipeIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeIngredientViewHolder holder, int position) {
        RecipeIngredient ingredient = ingredientList.get(position);
        holder.ingredientName.setText(ingredient.getIngredientName());
        holder.ingredientQuantityRequired.setText(formatQuantity(ingredient.getQuantityRequired()));
        holder.ingredientAvailableQuantity.setText("Have " + formatQuantity(ingredient.getAvailableQuantity()));
    }

    // 2.0 -> "2", 0.5 -> "0.5"
    private static String formatQuantity(double value) {
        if (value == Math.floor(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class RecipeIngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientQuantityRequired;
        TextView ingredientAvailableQuantity;

        public RecipeIngredientViewHolder(View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.riName);
            ingredientQuantityRequired = itemView.findViewById(R.id.riQuantityRequired);
            ingredientAvailableQuantity = itemView.findViewById(R.id.riAvailableQuantity);
        }
    }
}