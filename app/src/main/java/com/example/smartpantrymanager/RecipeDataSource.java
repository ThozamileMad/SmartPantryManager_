package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDataSource {
    private final RecipeDbHelper dbHelper;
    public RecipeDataSource(Context context) {
        dbHelper = new RecipeDbHelper(context);
    }

    public List<RecipeNameIngredientCount> getRecipeNameAndIngredientCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<RecipeNameIngredientCount> data = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                """
                SELECT
                  r.id,
                    r.name,
                    (
                       SELECT COUNT(*) 
                       FROM recipe_ingredient ri 
                       WHERE ri.recipe_id = r.id
                    ) AS ingredient_count
                FROM recipe r
                ORDER BY RANDOM()
                LIMIT 3
            """,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int ingredientCount = cursor.getInt(cursor.getColumnIndexOrThrow("ingredient_count")
            );

            RecipeNameIngredientCount recipeNameIngredientCount = new RecipeNameIngredientCount(
                    id,
                    name,
                    ingredientCount
            );
            data.add(recipeNameIngredientCount);
        }

        cursor.close();
        return data;
    }

    public List<RecipeWithMissingIngredients> getRecipeWithMissingIngredients() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                """
                    SELECT r.id, r.name, ri.ingredient_name, ri.quantity_required, ri.unit
                    FROM recipe r
                    JOIN recipe_ingredient ri ON r.id = ri.recipe_id
                    WHERE NOT EXISTS (
                        SELECT 1
                        FROM food f
                        WHERE LOWER(f.name) = LOWER(ri.ingredient_name)
                          AND f.unit = ri.unit
                          AND f.quantity >= ri.quantity_required
                    )
                    ORDER BY r.id
                """,
                null
        );

        Map<Integer, RecipeWithMissingIngredients> data = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            String ingredientName = cursor.getString(cursor.getColumnIndexOrThrow("ingredient_name"));
            if (!data.containsKey(id)) {
                RecipeWithMissingIngredients rwmi = new RecipeWithMissingIngredients(id, name);
                rwmi.addMissingIngredient(ingredientName);
                data.put(id, rwmi);
            } else {
                RecipeWithMissingIngredients updatedRwmi = data.get(id);
                updatedRwmi.addMissingIngredient(ingredientName);

                data.put(id, updatedRwmi);
            }
        }
        cursor.close();
        List<RecipeWithMissingIngredients> finalData = new ArrayList<>(data.values());
        return finalData;
    }

    public List<RecipeIngredient> getRecipeIngredient(int recipeId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("""
                SELECT
                    ri.id,
                    ri.ingredient_name,
                    ri.quantity_required,
                    (
                         SELECT
                            COALESCE(SUM(f.quantity), 0)
                         FROM food f
                         WHERE LOWER(ri.ingredient_name) = LOWER(f.name)
                     ) AS available_quantity
                FROM recipe_ingredient ri
                WHERE ri.recipe_id = ?
            """,
                new String[]{String.valueOf(recipeId)}
        );

        List<RecipeIngredient> data = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String ingredientName = cursor.getString(cursor.getColumnIndexOrThrow("ingredient_name"));
            double quantityRequired = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity_required"));
            double availableQuantity = cursor.getDouble(cursor.getColumnIndexOrThrow("available_quantity"));
            data.add(new RecipeIngredient(id, ingredientName, quantityRequired, availableQuantity));
        }

        cursor.close();


        return data;
    }

    public List<String> getRecipeInstructions(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT instructions FROM recipe WHERE id = ?",
                new String[]{String.valueOf(id)}
        );
        List<String> instructions = new ArrayList<>();
        while (cursor.moveToNext()) {
            String instructionStr = cursor.getString(
                    cursor
                            .getColumnIndexOrThrow("instructions"));

            if (instructionStr == null) {
                continue;
            }

            for (String step : instructionStr.split("\\|")) {
                String trimmed = step.trim();
                if (!trimmed.isEmpty()) {
                    instructions.add(trimmed);
                }
            }
        }
        cursor.close();
        return instructions;
    }
}