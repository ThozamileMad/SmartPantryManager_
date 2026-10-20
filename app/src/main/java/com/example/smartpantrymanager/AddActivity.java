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

public class AddActivity extends AppCompatActivity {
    // Intent
    Intent intent;

    // Input Fields
    EditText nameEdt;
    EditText qtyEdt;
    EditText unitEdt;
    EditText expDateEdt;

    // Error Text
    TextView errorTxt;

    // Buttons
    Button addSvBtn;

    // Clickable Navigation Fields
    private LinearLayout ptryNavField;
    private LinearLayout recipeNavField;
    private LinearLayout settingsNavField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameEdt = findViewById(R.id.addNameEdt);

        qtyEdt = findViewById(R.id.addQtyEdt);

        unitEdt = findViewById(R.id.addUnitEdt);

        expDateEdt = findViewById(R.id.addExpDateEdt);
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

        errorTxt = findViewById(R.id.addErrTxt);

        FormService fs = new FormService(this);

        addSvBtn = findViewById(R.id.addSvBtn);
        addSvBtn.setOnClickListener(v -> {

            String nameInput = String.valueOf(nameEdt.getText());
            String qtyInput = String.valueOf(qtyEdt.getText());
            String unitInput = String.valueOf(unitEdt.getText());
            String expDateInput = String.valueOf(expDateEdt.getText());

            boolean inserted = fs.insert(
                    errorTxt,
                    nameInput,
                    qtyInput,
                    unitInput,
                    expDateInput
            );

            if (inserted) {
                nameEdt.setText("");
                qtyEdt.setText("");
                unitEdt.setText("");
                expDateEdt.setText("");
            }

        });


        ptryNavField = findViewById(R.id.addPantryNavGroup);
        ptryNavField.setOnClickListener(v -> {
            intent = new Intent(this, PantryActivity.class);
            this.startActivity(intent);
        });

        recipeNavField = findViewById(R.id.addRecipesNavGroup);
        recipeNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            this.startActivity(intent);
        });

        settingsNavField = findViewById(R.id.addSettingsNavGroup);
        settingsNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
    }
}