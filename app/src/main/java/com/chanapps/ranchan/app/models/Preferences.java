package com.chanapps.ranchan.app.models;

import android.content.Context;
import android.preference.PreferenceManager;
import com.chanapps.ranchan.app.fragments.SettingsFragment;

/**
* Created by johnarleyburns on 01/07/14.
*/
public enum Preferences {
    PREF_ADULT("pref_adult"),
    PREF_NICKNAME("pref_nickname");
    public String prefKey;
    Preferences(String prefKey) {
        this.prefKey = prefKey;
    }
    public static boolean adultEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(SettingsFragment.PREF_ADULT, false);
    }
}
