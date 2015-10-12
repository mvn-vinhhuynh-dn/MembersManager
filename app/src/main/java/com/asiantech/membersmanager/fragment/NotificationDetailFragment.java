package com.asiantech.membersmanager.fragment;

import android.support.v4.view.ViewPager;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.ViewPagerDetailsAdapter;
import com.asiantech.membersmanager.models.Notification;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

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
    void afterViews() {
        mViewpagerDetailsAdapter = new ViewPagerDetailsAdapter(mNotifications, getActivity());
        viewpagerDetails.setAdapter(mViewpagerDetailsAdapter);
        viewpagerDetails.setCurrentItem(mPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(mNotifications.get(mPosition).getMTittle());
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }
}