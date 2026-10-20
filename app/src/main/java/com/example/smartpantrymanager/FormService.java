package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FormService {
    private final Context context;
    public FormService(Context context) {

        this.context = context;
    }
    public boolean validateNameInput(TextView errorTxt,
                 String nameInput
    ) {
        if (nameInput.isEmpty()) {
            errorTxt.setText("Name is required");
            return false;
        }
        else if (nameInput.length() > 50) {
            errorTxt.setText("Name must be 50 characters or less");
            return false;
        }
        else if (!nameInput.matches("^[a-zA-Z0-9 ]+$")) {
            errorTxt.setText("Name can only contain letters, numbers, and spaces");
            return false;
        }

        return true;
    }

    public boolean validateQtyInput(
            TextView errorTxt,
            String qtyInput
    ) {
        if (qtyInput.isEmpty()) {
            errorTxt.setText("Quantity is required");
            return false;
        }
        else if (!qtyInput.matches("^\\d+(\\.\\d+)?$")) {
            errorTxt.setText("Quantity must be a valid number");
            return false;
        }
        else if (Double.parseDouble(qtyInput) <= 0) {
            errorTxt.setText("Quantity must be greater than 0");
            return false;
        }
        else if (Double.parseDouble(qtyInput) > 99999) {
            errorTxt.setText("Quantity is too large");
            return false;
        }

        return true;
    }

    public boolean validateUnitInput(
            TextView errorTxt,
            String unitInput
    ) {
        if (unitInput.isEmpty()) {
            errorTxt.setText("Unit is required");
            return false;
        }
        else if (unitInput.length() > 20) {
            errorTxt.setText("Unit must be 20 characters or less");
            return false;
        }
        else if (!unitInput.matches("^[a-zA-Z]+$")) {
            errorTxt.setText("Unit can only contain letters");
            return false;
        }

        return true;
    }

    public boolean validateExpDateInput(
            TextView errorTxt,
            String expDateInput
    ) {
        if (expDateInput.isEmpty()) {
            errorTxt.setText("Expiry date is required");
            return false;
        }
        else if (!expDateInput.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            errorTxt.setText("Expiry date must be in YYYY-MM-DD format");
            return false;
        }

        return true;
    }


    public void edit(
            TextView errorTxt,
            int Id,
            String nameInput,
            String qtyInput,
            String unitInput,
            String expDateInput
    ) {
        boolean validName = validateNameInput(errorTxt, nameInput);
        if (!validName) {
            errorTxt.setVisibility(View.VISIBLE);
            return;
        }

        boolean validQty = validateQtyInput(errorTxt, qtyInput);
        if (!validQty) {
            errorTxt.setVisibility(View.VISIBLE);
            return;
        }

        boolean validUnit = validateUnitInput(errorTxt, unitInput);
        if (!validUnit) {
            errorTxt.setVisibility(View.VISIBLE);
            return;
        }

        boolean validExpDate = validateExpDateInput(errorTxt, expDateInput);
        if (!validExpDate) {
            errorTxt.setVisibility(View.VISIBLE);
            return;
        }

        errorTxt.setVisibility(View.GONE);

        FoodDataSource fds = new FoodDataSource(context);

        int updated = fds.updateFood(
                Id,
                nameInput,
                Double.parseDouble(qtyInput),
                unitInput,
                expDateInput
        );

        if (updated <= 0) {
            Toast.makeText(context, "Sorry there was an error with the system", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(
                    context,
                    "Ingredient Successfully Updated",
                    Toast.LENGTH_SHORT
            ).show();

        }
    }

    public void delete(int Id) {
        FoodDataSource fds = new FoodDataSource(context);

        int deleted = fds.deleteFood(Id);

        if (deleted > 0) {

            Toast.makeText(
                    context,
                    "Ingredient Successfully Deleted",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(context, PantryActivity.class);
            context.startActivity(intent);

        }
        else {
            Toast.makeText(context, "Sorry there was an error with the system",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    public boolean insert(TextView errorTxt, String nameInput, String qtyInput, String unitInput, String expDateInput
    ) {
        boolean validName = validateNameInput(errorTxt, nameInput);
        if (!validName) {
            errorTxt.setVisibility(View.VISIBLE);
            return false;
        }

        boolean validQty = validateQtyInput(errorTxt, qtyInput);
        if (!validQty) {
            errorTxt.setVisibility(View.VISIBLE);
            return false;
        }

        boolean validUnit = validateUnitInput(errorTxt, unitInput);
        if (!validUnit) {
            errorTxt.setVisibility(View.VISIBLE);
            return false;
        }

        boolean validExpDate = validateExpDateInput(errorTxt, expDateInput);
        if (!validExpDate) {
            errorTxt.setVisibility(View.VISIBLE);
            return false;
        }

        errorTxt.setVisibility(View.GONE);

        FoodDataSource fds = new FoodDataSource(context);

        long inserted = fds.insertFood(nameInput, Double.parseDouble(qtyInput), unitInput,
                expDateInput
        );

        if (inserted <= 0) {
            Toast.makeText(context,
                    "Sorry there was an error with the system, " +
                    "or you tried to add a pre-existing item with the same name",
                    Toast.LENGTH_LONG
            ).show();

            return false;

        } else {

            Toast.makeText(
                    context,
                    "Ingredient Successfully Added",
                    Toast.LENGTH_SHORT
            ).show();

            return true;
        }
    }
}
