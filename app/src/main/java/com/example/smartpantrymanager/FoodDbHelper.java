package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public FoodDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFoodTable = """
             CREATE TABLE food (
                 id INTEGER PRIMARY KEY AUTOINCREMENT,
                 name TEXT NOT NULL,
                 quantity INTEGER NOT NULL,
                 unit TEXT NOT NULL,
                 expiry_date TEXT
             )
        """;

        db.execSQL(createFoodTable);

        db.execSQL("""
            INSERT INTO food (name, quantity, unit, expiry_date) VALUES
                ('All  Purpose Flour', 5, 'kg', '2026-01-15'),
                ('Granulated Sugar', 2, 'kg', '2027-06-30'),
                ('Brown Sugar', 1, 'kg', '2026-11-20'),
                ('Kosher Salt', 3, 'kg', NULL),
                ('Black Pepper', 200, 'g', '2026-09-10'),
                ('Olive Oil', 1, 'L', '2025-12-01'),
                ('Vegetable Oil', 2, 'L', '2026-08-14'),
                ('White Rice', 10, 'kg', '2027-03-22'),
                ('Brown Rice', 4, 'kg', '2026-05-18'),
                ('Spaghetti', 6, 'boxes', '2026-10-05'),
                ('Penne', 3, 'boxes', '2027-01-30'),
                ('Canned Tomatoes', 12, 'cans', '2028-04-12'),
                ('Tomato Paste', 4, 'cans', '2027-07-08'),
                ('Black Beans', 8, 'cans', '2028-02-25'),
                ('Chickpeas', 6, 'cans', '2028-03-15'),
                ('Chicken Broth', 5, 'cartons', '2026-06-20'),
                ('Vegetable Broth', 4, 'cartons', '2026-09-01'),
                ('Peanut Butter', 2, 'jars', '2026-12-10'),
                ('Strawberry Jam', 1, 'jars', '2026-04-05'),
                ('Honey', 1, 'bottles', NULL),
                ('Maple Syrup', 1, 'bottles', '2026-07-19'),
                ('Oats', 3, 'kg', '2026-11-11'),
                ('Cornmeal', 2, 'kg', '2026-08-30'),
                ('Baking Powder', 150, 'g', '2026-03-28'),
                ('Baking Soda', 200, 'g', '2027-05-14'),
                ('Vanilla Extract', 100, 'ml', '2027-02-09'),
                ('Eggs', 12, 'pieces', '2026-01-05'),
                ('Milk', 2, 'L', '2025-12-15'),
                ('Butter', 500, 'g', '2026-02-20'),
                ('Cheddar Cheese', 400, 'g', '2026-03-10'),
                ('Garlic', 3, 'bulbs', '2026-01-25'),
                ('Onions', 5, 'pieces', '2026-02-28'),
                ('Potatoes', 5, 'kg', '2026-04-15'),
                ('Carrots', 2, 'kg', '2026-01-18'),
                ('Green Tea', 50, 'bags', '2027-09-22'),
                ('Coffee Beans', 1, 'kg', '2026-06-30'),
                ('Almonds', 500, 'g', '2026-10-12'),
                ('Walnuts', 300, 'g', '2026-09-25'),
                ('Raisins', 250, 'g', '2027-01-08'),
                ('Dark Chocolate', 200, 'g', '2026-12-24')
            """);
    }
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }

}
