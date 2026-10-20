package com.example.smartpantrymanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Single place to read/write app settings. Any screen can use it:
 *   boolean show = new SettingsManager(this).showAlmostThere();
 */
public class SettingsManager {
    private static final String PrefName = "smart_pantry_settings";

    private static final String k_exp_alts = "expiry_alerts";
    private static final String keyShowAlmostThree = "show_almost_there";
    private static final String useImp = "use_imperial";

    private final SharedPreferences prefs;

    public SettingsManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PrefName, Context.MODE_PRIVATE);
    }

    public boolean expiryAlertsEnabled() {
        return prefs.getBoolean(
                k_exp_alts, false);
    }
    public void setExpiryAlertsEnabled(boolean enabled) {
        prefs.edit().putBoolean(k_exp_alts, enabled).apply();
    }
    public boolean showAlmostThere() {

        return prefs.getBoolean(keyShowAlmostThree, true);
    }




    public void setShowAlmostThere(boolean enabled) {
        prefs.edit().putBoolean(keyShowAlmostThree, enabled).apply();
    }


















    public boolean useImperial() {

        return prefs.getBoolean(useImp, false);
    }


    public void setUseImperial(boolean imperial) {
        prefs.edit().putBoolean(useImp, imperial).apply();
    }
}
