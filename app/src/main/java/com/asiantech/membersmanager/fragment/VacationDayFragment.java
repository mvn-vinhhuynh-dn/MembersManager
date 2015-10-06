package com.asiantech.membersmanager.fragment;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_vacation)
public class VacationDayFragment extends BaseFragment {
    @Override
    public void onResume() {
        super.onResume();
        if(mOnBaseFragmentListener != null) mOnBaseFragmentListener.setTitleHeader(getString(R.string.vacation_day));
    }
}