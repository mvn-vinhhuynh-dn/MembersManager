package com.asiantech.membersmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.fragment.DrawerFragment;
import com.asiantech.membersmanager.fragment.DrawerFragment_;
import com.asiantech.membersmanager.fragment.FavoriteFragment_;
import com.asiantech.membersmanager.fragment.HelpAndFeedBackFragment_;
import com.asiantech.membersmanager.fragment.HomeFragment_;
import com.asiantech.membersmanager.fragment.NotificationDetailFragment;
import com.asiantech.membersmanager.fragment.TimeSheetFragment_;
import com.asiantech.membersmanager.fragment.VacationDayFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 12-03-2015.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements DrawerFragment.FragmentDrawerListener, BaseFragment.OnBaseFragmentListener {
    private DrawerFragment mDrawerFragment;
    @ViewById(R.id.toolbar)
    Toolbar mToolBar;

    @AfterViews
    public void afterViews() {
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_profile);
        }
        initView();
        initListener();
        displayView(0);
        setTitle(getString(R.string.title_home));
    }


    private void initView() {
        mDrawerFragment = (DrawerFragment_)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolBar);
    }

    private void initListener() {
        mDrawerFragment.setDrawerListener(this);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment_();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FavoriteFragment_();
                title = getString(R.string.title_favorite);
                break;
            case 2:
                fragment = new TimeSheetFragment_();
                title = getString(R.string.time_sheet);
                break;
            case 3:
                fragment = new VacationDayFragment_();
                title = getString(R.string.vacation_day);
                break;
            case 4:
                fragment = new HelpAndFeedBackFragment_();
                title = getString(R.string.help_feedback);
                break;
            case 5:
                //TOdo Logout function
                break;
            default:
                break;
        }
        keepHomeFragment();
        if (fragment != null) {
            changeFragment(fragment, title, false);
            setTitle(title);
        }
    }

    private void keepHomeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                if (i > 0) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void setTitleHeader(String title) {
        setTitle(title);
    }

    public void changeFragment(Fragment fragment, String title, boolean isBack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        //Add to back stack
        if (!isBack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    private void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar()
                    .setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_body);
        if (fragment instanceof NotificationDetailFragment) {

        }
        if (getSupportFragmentManager().getBackStackEntryCount() != 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}