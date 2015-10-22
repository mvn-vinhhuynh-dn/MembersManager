package com.asiantech.membersmanager;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.asiantech.membersmanager.network.MySession;
import com.asiantech.membersmanager.network.core.ApiClient;
import com.asiantech.membersmanager.network.core.ApiConfig;

import java.util.Locale;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/21/15.
 */
public class App extends Application {
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale("vn");
        res.updateConfiguration(conf, dm);

        ApiConfig apiConfig = ApiConfig.builder(getApplicationContext())
                .baseUrl(getResources().getString(R.string.url_base))
                .sessionStore(new MySession())
                .build();
        ApiClient.getInstance().init(apiConfig);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }
}
