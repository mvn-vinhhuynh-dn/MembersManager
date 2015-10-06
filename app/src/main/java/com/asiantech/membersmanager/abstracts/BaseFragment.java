package com.asiantech.membersmanager.abstracts;

import android.support.v4.app.Fragment;

import com.asiantech.membersmanager.MainActivity_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
public abstract class BaseFragment extends Fragment {
    protected void replaceFragment(Fragment fragment, String title, boolean isBack) {
        if (getActivity() != null) {
            ((MainActivity_) getActivity()).changeFragment(fragment, title, isBack);
        }
    }
}
