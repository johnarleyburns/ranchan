package com.chanapps.ranchan.app.fragments;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import com.chanapps.ranchan.app.R;


public class SettingsFragment extends PreferenceFragment {

    public static final String PREF_ADULT = "pref_adult";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        Preference adult = findPreference(PREF_ADULT);
        adult.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                sendRefreshThreadListBrodacast();
                return true;
            }
        });
    }

    private void sendRefreshThreadListBrodacast() {
        Context context = getActivity();
        if (context != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ThreadListFragment.REFRESH_THREAD_LIST_ACTION));
        }
    }

}
