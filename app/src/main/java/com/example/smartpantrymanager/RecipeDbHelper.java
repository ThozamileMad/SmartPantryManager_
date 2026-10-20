package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String createRecipeTable = """
            CREATE TABLE recipe (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                instructions TEXT NOT NULL
            )
        """;
        db.execSQL(createRecipeTable);
        db.execSQL("""
            CREATE TABLE recipe_ingredient (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                recipe_id INTEGER NOT NULL,
                ingredient_name TEXT NOT NULL,
                quantity_required REAL NOT NULL,
                unit TEXT NOT NULL,
                FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE
            )
        """);

        db.execSQL("""
            INSERT INTO recipe (name, instructions) VALUES
           ('Classic Pancakes', 'Mix flour, sugar, baking powder.|Whisk in eggs and milk.|Melt butter into batter.|Cook spoonfuls on a hot pan until golden on both sides.'),
           ('Spaghetti with Tomato Sauce', 'Boil spaghetti until al dente.|Saute garlic and onion in olive oil.|Add canned tomatoes and tomato paste, simmer 15 min.|Toss with drained spaghetti.'),
           ('Penne Arrabbiata', 'Boil penne until al dente.|Saute garlic in olive oil with black pepper.|Add canned tomatoes and tomato paste, simmer.|Combine with penne.'),
           ('Chickpea Stew', 'Saute onion and garlic in olive oil.|Add chickpeas, canned tomatoes and vegetable broth.|Simmer 20 minutes until thickened.'),
           ('Black Bean Soup', 'Saute onion and garlic.|Add black beans and vegetable broth.|Simmer 15 min, season with black pepper.'),
           ('Garlic Chicken Broth Rice', 'Saute onion and garlic.|Add white rice and chicken broth.|Cover and simmer until rice is tender.'),
           ('Vegetable Fried Rice', 'Saute carrots, onion and garlic in vegetable oil.|Add cooked brown rice.|Season with black pepper and stir-fry until hot.'),
           ('Simple Mashed Potatoes', 'Boil potatoes until soft.|Drain and mash with butter and milk.|Season with kosher salt.'),
           ('Skillet Cornbread', 'Mix cornmeal, flour, sugar and baking powder.|Whisk in eggs and milk.|Stir in melted butter.|Bake until golden.'),
           ('Honey Oatmeal', 'Cook oats with milk over low heat.|Stir until creamy.|Sweeten with honey.'),
           ('Peanut Butter Oat Bars', 'Mix oats with peanut butter and honey.|Fold in raisins.|Press into a tray and chill until firm.'),
           ('Chocolate Almond Cookies', 'Cream butter and sugar.|Beat in eggs.|Mix in flour and baking soda.|Fold in dark chocolate and almonds.|Bake until set.'),
           ('Walnut Raisin Cookies', 'Cream butter and sugar.|Beat in eggs.|Mix in flour and baking soda.|Fold in walnuts and raisins.|Bake until golden.'),
           ('Cheesy Garlic Rice', 'Saute garlic in butter.|Stir in cooked white rice and cheddar cheese until melted.|Season with black pepper.'),
           ('Root Vegetable Broth Soup', 'Saute onion and garlic.|Add carrots, potatoes and vegetable broth.|Simmer until vegetables are tender.'),
           ('Maple Glazed Carrots', 'Steam carrots until tender.|Toss in a pan with butter and maple syrup.|Season with kosher salt.'),
           ('Honey Roasted Almonds', 'Toss almonds with honey and a pinch of kosher salt.|Roast until golden and fragrant.'),
           ('Garlic Butter Spaghetti', 'Boil spaghetti until al dente.|Melt butter with olive oil and garlic.|Toss spaghetti through, season with black pepper.'),
           ('Chickpea and Rice Bowl', 'Cook white rice.|Warm chickpeas with garlic in olive oil.|Combine and season with black pepper.'),
           ('Brown Rice and Bean Bowl', 'Cook brown rice.|Warm black beans with onion and garlic.|Combine and season with black pepper.');
          """);

        db.execSQL("""
            INSERT INTO recipe_ingredient (recipe_id, ingredient_name, quantity_required, unit) VALUES
            -- Classic Pancakes (1)
            (1, 'All-Purpose Flour', 0.3, 'kg'), (1, 'Granulated Sugar', 0.05, 'kg'), (1, 'Baking Powder', 10, 'g'), (1, 'Eggs', 2, 'pieces'), (1, 'Milk', 0.3, 'L'), (1, 'Butter', 30, 'g'),
            -- Spaghetti with Tomato Sauce (2)
            (2, 'Spaghetti', 1, 'boxes'), (2, 'Canned Tomatoes', 2, 'cans'), (2, 'Tomato Paste', 1, 'cans'), (2, 'Garlic', 1, 'bulbs'), (2, 'Onions', 1, 'pieces'), (2, 'Olive Oil', 0.05, 'L'),
            -- Penne Arrabbiata (3)
            (3, 'Penne', 1, 'boxes'), (3, 'Canned Tomatoes', 2, 'cans'), (3, 'Tomato Paste', 1, 'cans'), (3, 'Garlic', 1, 'bulbs'), (3, 'Black Pepper', 5, 'g'), (3, 'Olive Oil', 0.05, 'L'),
            -- Chickpea Stew (4)
            (4, 'Chickpeas', 2, 'cans'), (4, 'Canned Tomatoes', 1, 'cans'), (4, 'Onions', 1, 'pieces'), (4, 'Garlic', 1, 'bulbs'), (4, 'Vegetable Broth', 1, 'cartons'), (4, 'Olive Oil', 0.03, 'L'),
            -- Black Bean Soup (5)
            (5, 'Black Beans', 2, 'cans'), (5, 'Vegetable Broth', 1, 'cartons'), (5, 'Onions', 1, 'pieces'), (5, 'Garlic', 1, 'bulbs'), (5, 'Black Pepper', 5, 'g'),
            -- Garlic Chicken Broth Rice (6)
            (6, 'White Rice', 0.5, 'kg'), (6, 'Chicken Broth', 1, 'cartons'), (6, 'Onions', 1, 'pieces'), (6, 'Garlic', 1, 'bulbs'),
            -- Vegetable Fried Rice (7)
            (7, 'Brown Rice', 0.5, 'kg'), (7, 'Carrots', 0.3, 'kg'), (7, 'Onions', 1, 'pieces'), (7, 'Garlic', 1, 'bulbs'), (7, 'Vegetable Oil', 0.03, 'L'), (7, 'Black Pepper', 5, 'g'),
            -- Simple Mashed Potatoes (8)
            (8, 'Potatoes', 1, 'kg'), (8, 'Butter', 50, 'g'), (8, 'Milk', 0.2, 'L'), (8, 'Kosher Salt', 5, 'g'),
            -- Skillet Cornbread (9)
            (9, 'Cornmeal', 0.3, 'kg'), (9, 'All-Purpose Flour', 0.2, 'kg'), (9, 'Granulated Sugar', 0.05, 'kg'), (9, 'Baking Powder', 10, 'g'), (9, 'Eggs', 2, 'pieces'), (9, 'Milk', 0.3, 'L'), (9, 'Butter', 40, 'g'),
            -- Honey Oatmeal (10)
            (10, 'Oats', 0.1, 'kg'), (10, 'Milk', 0.25, 'L'), (10, 'Honey', 20, 'ml'),
            -- Peanut Butter Oat Bars (11)
            (11, 'Oats', 0.2, 'kg'), (11, 'Peanut Butter', 1, 'jars'), (11, 'Honey', 30, 'ml'), (11, 'Raisins', 50, 'g'),
            -- Chocolate Almond Cookies (12)
            (12, 'All-Purpose Flour', 0.25, 'kg'), (12, 'Granulated Sugar', 0.15, 'kg'), (12, 'Butter', 100, 'g'), (12, 'Eggs', 1, 'pieces'), (12, 'Baking Soda', 5, 'g'), (12, 'Dark Chocolate', 100, 'g'), (12, 'Almonds', 100, 'g'),
            -- Walnut Raisin Cookies (13)
            (13, 'All-Purpose Flour', 0.25, 'kg'), (13, 'Granulated Sugar', 0.15, 'kg'), (13, 'Butter', 100, 'g'), (13, 'Eggs', 1, 'pieces'), (13, 'Baking Soda', 5, 'g'), (13, 'Walnuts', 100, 'g'), (13, 'Raisins', 80, 'g'),
            -- Cheesy Garlic Rice (14)
            (14, 'Garlic', 1, 'bulbs'), (14, 'Butter', 30, 'g'), (14, 'White Rice', 0.4, 'kg'), (14, 'Cheddar Cheese', 100, 'g'), (14, 'Black Pepper', 5, 'g'),
            -- Root Vegetable Broth Soup (15)
            (15, 'Onions', 1, 'pieces'), (15, 'Garlic', 1, 'bulbs'), (15, 'Carrots', 0.3, 'kg'), (15, 'Potatoes', 0.5, 'kg'), (15, 'Vegetable Broth', 1, 'cartons'),
            -- Maple Glazed Carrots (16)
            (16, 'Carrots', 0.4, 'kg'), (16, 'Butter', 20, 'g'), (16, 'Maple Syrup', 30, 'ml'), (16, 'Kosher Salt', 3, 'g'),
            -- Honey Roasted Almonds (17)
            (17, 'Almonds', 150, 'g'), (17, 'Honey', 20, 'ml'), (17, 'Kosher Salt', 2, 'g'),
            -- Garlic Butter Spaghetti (18)
            (18, 'Spaghetti', 1, 'boxes'), (18, 'Butter', 30, 'g'), (18, 'Olive Oil', 0.02, 'L'), (18, 'Garlic', 1, 'bulbs'), (18, 'Black Pepper', 5, 'g'),
            -- Chickpea and Rice Bowl (19)
            (19, 'White Rice', 0.4, 'kg'), (19, 'Chickpeas', 1, 'cans'), (19, 'Garlic', 1, 'bulbs'), (19, 'Olive Oil', 0.02, 'L'), (19, 'Black Pepper', 5, 'g'),
            -- Brown Rice and Bean Bowl (20)
            (20, 'Brown Rice', 0.4, 'kg'), (20, 'Black Beans', 1, 'cans'), (20, 'Onions', 1, 'pieces'), (20, 'Garlic', 1, 'bulbs'), (20, 'Black Pepper', 5, 'g'),
            -- 21: missing TWO ingredients (Shrimp, Lemon)
            (21, 'Spaghetti', 1, 'boxes'), (21, 'Garlic', 1, 'bulbs'), (21, 'Butter', 30, 'g'), (21, 'Shrimp', 200, 'g'), (21, 'Lemon', 1, 'pieces'),
            -- 22: missing ONE ingredient (Banana) - good for the "Almost There" bonus list
            (22, 'Oats', 0.1, 'kg'), (22, 'Milk', 0.25, 'L'), (22, 'Peanut Butter', 1, 'jars'), (22, 'Banana', 1, 'pieces'),
            -- 23: all ingredients exist, but Eggs needed (20) exceeds pantry stock (12)
            (23, 'Eggs', 20, 'pieces'), (23, 'Milk', 0.5, 'L'), (23, 'Butter', 50, 'g')
        """);

    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        db.execSQL("DROP TABLE IF EXISTS recipe");
        db.execSQL("DROP TABLE IF EXISTS recipe_ingredient");
        onCreate(db);
    }

}

