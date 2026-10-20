package com.example.smartpantrymanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity {
    private SettingsManager settings;
    private SwitchCompat expiryAlertsSwitch;
    private TextView unitSystemDescription;
    private TextView versionText;
    private LinearLayout ptryNavField;
    private SwitchCompat unitSystemSwitch;
    private LinearLayout recipeNavField;
    private LinearLayout settingsNavField;

    private SwitchCompat almostThereSwitch;

    private final ActivityResultLauncher<String> notificationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) {
                    settings.setExpiryAlertsEnabled(true);
                } else {
                    settings.setExpiryAlertsEnabled(false);
                    expiryAlertsSwitch.setChecked(false);
                    Toast.makeText(this,
                            "Allow notifications to get expiry alerts",
                            Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings = new SettingsManager(this);
        expiryAlertsSwitch = findViewById(R.id.stExpiryAlertsSwitch);
        almostThereSwitch = findViewById(R.id.stAlmostThereSwitch);
        unitSystemSwitch = findViewById(R.id.stUnitSystemSwitch);
        unitSystemDescription = findViewById(R.id.stUnitSystemDesc);
        versionText = findViewById(R.id.stVersionDesc);
        loadSettings();
        setUpSwitchListeners();
        ptryNavField = findViewById(R.id.stPtryNavGroup);
        ptryNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, PantryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        recipeNavField = findViewById(R.id.stRecipesNavGroup);
        recipeNavField.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        settingsNavField = findViewById(R.id.stSettingsNavGroup);
        versionText.setText("Version " + getAppVersion());
    }
    private void loadSettings() {
        expiryAlertsSwitch.setChecked(settings.expiryAlertsEnabled());
        almostThereSwitch.setChecked(settings.showAlmostThere());
        unitSystemSwitch.setChecked(settings.useImperial());
        updateUnitSystemText(settings.useImperial());
    }

    private void setUpSwitchListeners() {
        expiryAlertsSwitch.setOnCheckedChangeListener((button, isChecked) -> {
            if (isChecked && needsNotificationPermission()) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                settings.setExpiryAlertsEnabled(isChecked);
            }
        });

        almostThereSwitch.setOnCheckedChangeListener((button, isChecked) -> settings.setShowAlmostThere(isChecked));
        unitSystemSwitch.setOnCheckedChangeListener((button, isChecked) -> {
            settings.setUseImperial(isChecked);
            updateUnitSystemText(isChecked);
        });
    }
    private boolean needsNotificationPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED;
    }
    private void updateUnitSystemText(boolean imperial) {
        unitSystemDescription.setText(imperial ? "Currently: Imperial" : "Currently: Metric");
    }



    private String getAppVersion() {
        try {
            return getPackageManager()
                    .getPackageInfo(getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "1.0";
        }
    }
}
