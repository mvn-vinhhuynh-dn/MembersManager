package com.asiantech.membersmanager.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.ViewPagerDetailsAdapter;
import com.asiantech.membersmanager.models.Notification;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.*;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_detail_notification)
public class NotificationDetailFragment extends BaseFragment {

    @ViewById(R.id.viewpagerDetails)
    ViewPager viewpagerDetails;
    private ViewPagerDetailsAdapter mViewpagerDetailsAdapter;

    @FragmentArg
    ArrayList<Notification> mNotifications;
    @FragmentArg
    int mPosition;

    @AfterViews
    void afterViews(){
        mViewpagerDetailsAdapter = new ViewPagerDetailsAdapter(mNotifications,getActivity());
        viewpagerDetails.setAdapter(mViewpagerDetailsAdapter);
        viewpagerDetails.setCurrentItem(mPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null)
            mOnBaseFragmentListener.setTitleHeader(mNotifications.get(mPosition).getMTittle());
  }
}