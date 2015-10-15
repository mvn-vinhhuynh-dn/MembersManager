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
        //set adapter
        setAdapter();
        //setDeault position
        viewpagerDetails.setCurrentItem(mPosition);
        //add listener
        initListener();
    }

    private void setAdapter() {
        mViewpagerDetailsAdapter = new ViewPagerDetailsAdapter(mNotifications, getActivity());
        viewpagerDetails.setAdapter(mViewpagerDetailsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.detail));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DETAILS);
        }
    }

    public boolean checkFavorite() {
        return mNotifications.get(mPosition).getIsFavorite();
    }

    public void changeFavorite() {
        if (mNotifications.get(mPosition).getIsFavorite()) {
            mNotifications.get(mPosition).setIsFavorite(false);
        } else {
            mNotifications.get(mPosition).setIsFavorite(true);
        }
    }

    private void initListener() {
        viewpagerDetails.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                mOnBaseFragmentListener.setTitleHeader(getString(R.string.hot_detail));
                mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DETAILS);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}