package com.asiantech.membersmanager.fragment;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_help)
public class HelpAndFeedBackFragment extends BaseFragment {
    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.help_feedback));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }
}