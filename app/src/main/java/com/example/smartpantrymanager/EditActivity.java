package com.example.smartpantrymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
    Intent intent;
    int foodId;
    String foodName;
    double foodQuantity;
    String foodUnit;
    String foodExpiryDate;


    EditText nameEdt;
    EditText qtyEdt;
    EditText unitEdt;
    EditText expDateEdt;
    TextView errorTxt;
    Button edtSvBtn;
    Button delBtn;

    private LinearLayout ptryNavField;
    private LinearLayout recipeNavField;
    private LinearLayout settingsNavField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        intent = getIntent();

        foodId = intent.getIntExtra("food_id", -1);
        foodName = intent.getStringExtra("food_name");
        foodQuantity = intent.getIntExtra("food_quantity", 0);
        foodUnit = intent.getStringExtra("food_unit");
        foodExpiryDate = intent.getStringExtra("food_expiry_date");

        nameEdt = findViewById(R.id.edtNameEdt);
        nameEdt.setText(foodName);

        qtyEdt = findViewById(R.id.edtQtyEdt);
        qtyEdt.setText(String.valueOf(foodQuantity));

        unitEdt = findViewById(R.id.edtUnitEdt);
        unitEdt.setText(foodUnit);

        expDateEdt = findViewById(R.id.edtExpDateEdt);
        expDateEdt.setText(foodExpiryDate);
        expDateEdt.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String date = String.format(
                                "%d-%02d-%02d",
                                selectedYear,
                                selectedMonth + 1,
                                selectedDay
                        );

                        expDateEdt.setText(date);
                    },
                    year,
                    month,
                    day
            );

            datePickerDialog.show();

        });

        errorTxt = findViewById(R.id.edtErrTxt);

        FormService fs = new FormService(this);

        edtSvBtn = findViewById(R.id.edtSvBtn);
        edtSvBtn.setOnClickListener(v -> {

            String nameInput = String.valueOf(nameEdt.getText());
            String qtyInput = String.valueOf(qtyEdt.getText());
            String unitInput = String.valueOf(unitEdt.getText());
            String expDateInput = String.valueOf(expDateEdt.getText());

            fs.edit(
                    errorTxt,
                    foodId,
                    nameInput,
                    qtyInput,
                    unitInput,
                    expDateInput
            );

        });

        delBtn = findViewById(R.id.edtDelBtn);
        delBtn.setOnClickListener(v -> {

            fs.delete(foodId);

        });

        ptryNavField = findViewById(R.id.edtPantryNavGroup);
        ptryNavField.setOnClickListener(v -> {
            intent = new Intent(this, PantryActivity.class);
            this.startActivity(intent);
        });

        recipeNavField = findViewById(R.id.edtRecipesNavGroup);
        recipeNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            this.startActivity(intent);
        });

        settingsNavField = findViewById(R.id.edtSettingsNavGroup);
        settingsNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
    }
}