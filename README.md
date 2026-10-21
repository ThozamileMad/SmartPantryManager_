# Smart Pantry Manager

An Android app in Java that helps you waste less food. You add the
ingredients you have at home, and the app shows you recipes you can cook
right now using only those ingredients.

The main rule: a recipe only shows up as suggested if you have every
ingredient it needs, in the right amount. If you're missing even one thing,
it doesn't appear in the suggestions list.

---

## What the app does

- Add, edit and delete pantry items (name, quantity, unit, expiry date)
- Pantry list that loads from the database
- 20 and more recipes that come pre loaded the first time you open the app
- Suggested recipes screen that only shows what you can actually cook
- An almost there list showing recipes that are missing just one ingredient
 Recipe detail screen with ingredients and step-by-step method
- Settings screen for expiry alerts, the almost there list, and units
- Shows a message when nothing matches your pantry yet.

---

## Why I used SQLite

I chose SQLite because the pantry data belongs on the phone. It's personal
stuff, there's no reason to send it to a server, and the app needs to work
without internet (a kitchen often doesn't have good Wi-Fi anyway). It's also
easier to check the strict matching rule when both the pantry and the
recipes are in the same databaseI can use a SQL query instead of pulling
data across a network. The brief also lists SQLite as the local option that
matches what we did in the module.

The data is saved in smart_pantry.db on the device, so it's still there
after you close and reopen the app. Settings are saved in SharedPreferences.

---

## How the matching works

For each recipe, the app checks every ingredient it needs against the
pantry. If all of them are there in enough quantity, the recipe is shown as
suggested. If any one is missing, it's left out. The almost there list
only shows recipes that are missing exactly one ingredient, and it is kept
separate from the main suggestions.

---

## Running the project

You need Android Studio and a device or emulator on API 24 or higher.

1. Clone the repo: git clone https://github.com/ThozamileMad/SmartPantryManager_.git
2. Open the folder in Android Studio.
3. Let Gradle finish syncing.
4. Hit Run.

The app sets up its own database and recipes the first time it runs, so
there's nothing to configure.

---

## Project files

MainActivity.java - entry point
PantryActivity.java - pantry list screen
AddActivity.java - add ingredient screen
EditActivity.java - edit delete ingredient
RecipesActivity.java - sugested recipes screen
RecipeViewActivity.java - recipe detail screen
SettingsActivity.java - settings screen

Food.java - pantry item
FoodAdapter.java - recycler view adapter for pantry
FoodDataSource.java - database oprations for food
FoodDbHelper.java - creates the food table

Recipe.java - recipe item
RecipeDataSource.java - recipe queries and matching
RecipeDbHelper.java - creates recipe tables and seeds data
AvailableRecipesAdapter.java - suggested recipes adapter
UnavailableRecipesAdapter.java - almost there adapter
RecipeIngredientAdapter.java - ingredient rows on detail screen
RecipeInstructionsAdapter.java - method steps adapter

FormService.java - input validation and form actions
SettingsManager.java - saves and reads settings


---

## Database tables

food - the user's pantry items
recipe: recipe name and steps
recipe_ingredient - each recipe's ingredients and amounts

---

## Author

Thozamile Motheo Madela
Richfield Graduate Institute of Technology
Mobile App Development 700
