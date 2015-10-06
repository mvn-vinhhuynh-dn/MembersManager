package com.asiantech.membersmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.asiantech.membersmanager.fragment.DrawerFragment;
import com.asiantech.membersmanager.fragment.DrawerFragment_;
import com.asiantech.membersmanager.fragment.FavoriteFragment_;
import com.asiantech.membersmanager.fragment.HelpAndFeedBackFragment_;
import com.asiantech.membersmanager.fragment.HomeFragment_;
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
public class MainActivity extends ActionBarActivity implements DrawerFragment.FragmentDrawerListener {
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
        callDefaultFragment();
    }

    private void callDefaultFragment() {
        Fragment fragment = new HomeFragment_();
        changeFragment(fragment, getString(R.string.home), false);
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

        if (fragment != null) {
            changeFragment(fragment, title, true);
        }
    }

    public void changeFragment(Fragment fragment, String title, boolean isBack) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);

        //Add to back stack
        if (!isBack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
        //Set the toolbar title
        setTitleFragment(title);
    }

    private FragmentTransaction getFragmentTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        return fragmentTransaction;
    }

    private void setTitleFragment(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar()
                    .setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}