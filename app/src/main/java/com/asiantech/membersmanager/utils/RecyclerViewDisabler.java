package com.asiantech.membersmanager.utils;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/19/15.
 */
public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}