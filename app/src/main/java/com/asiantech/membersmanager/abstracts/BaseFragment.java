package com.asiantech.membersmanager.abstracts;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.asiantech.membersmanager.MainActivity_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
public abstract class BaseFragment extends Fragment {
    protected OnBaseFragmentListener mOnBaseFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnBaseFragmentListener = (OnBaseFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBaseFragmentListener");
        }
    }

    protected void replaceFragment(Fragment fragment, boolean isBack) {
        if (getActivity() != null) {
            ((MainActivity_) getActivity()).changeFragment(fragment, isBack);
        }
    }

    public interface OnBaseFragmentListener {
        void setTitleHeader(String title);
    }
}