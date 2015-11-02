package com.asiantech.membersmanager.utils;

import android.app.Activity;
import android.os.Handler;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/2/15.
 */
public class Delay {
    public static void delay(Activity mActivity, int seconds) {
        final int milliseconds = seconds * 1000;
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Todo
                    }
                }, milliseconds);
            }
        });
    }
}
