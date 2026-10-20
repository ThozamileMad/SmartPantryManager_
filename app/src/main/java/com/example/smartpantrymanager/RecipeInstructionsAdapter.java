package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeInstructionsAdapter extends RecyclerView.Adapter<RecipeInstructionsAdapter.RecipeViewHolder> {
    private Context context;
    private List<String> recipeInstructionsList;

    public RecipeInstructionsAdapter(Context context, List<String> recipeInstructionsList) {
        this.context = context;
        this.recipeInstructionsList = recipeInstructionsList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new RecipeViewHolder(LayoutInflater
                .from(
                        parent.getContext()
                )
                .inflate(
                        R.layout.instructions_item,
                        parent,
                        false
                ));
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        String instruction = recipeInstructionsList.get(position);

        holder.recipeInstructionNo.setText((position + 1) + ".");



        holder.recipeInstruction.setText(instruction);
    }

    @Override
    public int getItemCount() {
        return recipeInstructionsList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeInstructionNo;
        TextView recipeInstruction;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeInstructionNo = itemView.findViewById(R.id.rvNumber);
            recipeInstruction = itemView.findViewById(R.id.rvInstruction);
        }
    }
}