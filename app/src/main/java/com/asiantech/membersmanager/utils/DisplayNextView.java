package com.asiantech.membersmanager.utils;

import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/16/15.
 */
public final class DisplayNextView implements Animation.AnimationListener {
    private boolean mCurrentView;
    ImageView image1;
    ImageView image2;

    public DisplayNextView(boolean currentView, ImageView image1, ImageView image2) {
        mCurrentView = currentView;
        this.image1 = image1;
        this.image2 = image2;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        image1.post(new SwapViews(mCurrentView, image1, image2));
    }

    public void onAnimationRepeat(Animation animation) {
    }
}