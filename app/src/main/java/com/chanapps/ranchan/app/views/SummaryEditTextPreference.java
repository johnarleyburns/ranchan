package com.chanapps.ranchan.app.views;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

/**
 * Created by johnarleyburns on 01/07/14.
 */
public class SummaryEditTextPreference extends EditTextPreference {
    public SummaryEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SummaryEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setSummary(text);
    }
}