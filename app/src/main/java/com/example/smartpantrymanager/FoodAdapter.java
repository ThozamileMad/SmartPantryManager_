package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context context;
    private List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent,  false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodQuantity.setText(String.valueOf(food.getQuantity()));
        holder.foodUnit.setText(food.getUnit());
        holder.foodExpiryStatus.setText(food.getExpiryStatus());
        holder.foodContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("food_id", food.getId());
            intent.putExtra("food_name", food.getName());
            intent.putExtra("food_quantity", food.getQuantity());
            intent.putExtra("food_unit", food.getUnit());
            intent.putExtra("food_expiry_date", food.getExpiryDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout foodContainer;
        TextView foodName;
        TextView foodQuantity;
        TextView foodUnit;
        TextView foodExpiryStatus;

        public FoodViewHolder(View itemView) {
            super(itemView);

            foodContainer = itemView.findViewById(R.id.fdPantryItem);
            foodName = itemView.findViewById(R.id.fdName);
            foodQuantity = itemView.findViewById(R.id.fdQuantity);
            foodUnit = itemView.findViewById(R.id.fdUnit);
            foodExpiryStatus= itemView.findViewById(R.id.fdExpiryStatus);
        }
    }
}