package com.chanapps.ranchan.app.views;



import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.chanapps.ranchan.app.R;


public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
