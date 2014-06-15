package com.chanapps.ranchan.app.application;

/**
 * Created by johnarleyburns on 14/06/14.
 */

import android.app.Application;
import android.content.Context;

public class RanchanApplication extends Application {
    private static RanchanApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
    }

    public static RanchanApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}