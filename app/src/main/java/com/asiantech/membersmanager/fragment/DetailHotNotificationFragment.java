package com.asiantech.membersmanager.fragment;

import android.support.v4.view.ViewPager;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.ViewPagerDetailsAdapter;
import com.asiantech.membersmanager.adapter.ViewPagerDetailsHotAdapter;
import com.asiantech.membersmanager.models.Notification;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/12/15.
 */
@EFragment(R.layout.fragment_detail_hot_notification)
public class DetailHotNotificationFragment extends BaseFragment {
    @ViewById(R.id.viewpagerDetailsHot)
    ViewPager viewpagerDetailsHot;
    private ViewPagerDetailsHotAdapter mViewpagerDetailsHotAdapter;

    @FragmentArg
    ArrayList<Notification> mNotifications;
    @FragmentArg
    int mPosition;

    @AfterViews
    void afterViews() {
        mViewpagerDetailsHotAdapter = new ViewPagerDetailsHotAdapter(mNotifications, getActivity());
        viewpagerDetailsHot.setAdapter(mViewpagerDetailsHotAdapter);
        viewpagerDetailsHot.setCurrentItem(mPosition);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.hot_detail));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }
}
