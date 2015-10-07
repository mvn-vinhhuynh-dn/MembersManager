package com.asiantech.membersmanager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.ReasonDayOffAdapter;
import com.asiantech.membersmanager.models.Reason;
import com.asiantech.membersmanager.utils.MyLinearLayoutManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_vacation)
public class VacationDayFragment extends BaseFragment implements ReasonDayOffAdapter.OnChooseReason {
    private ReasonDayOffAdapter mAdapter;
    private ArrayList<Reason> mDatas = new ArrayList<>();
    private ArrayList<Reason> mReasonChooseds = new ArrayList<>();
    @ViewById(R.id.rcViewResons)
    RecyclerView mRecycleViewReasons;
    @ViewById(R.id.edtCc)
    EditText mEdtCc;
    @ViewById(R.id.edtSubject)
    EditText mEdtSubject;
    @ViewById(R.id.edtTo)
    EditText mEdtTo;
    @ViewById(R.id.cbDifferentReson)
    CheckBox mCbChooseDifferentReason;
    @ViewById(R.id.edtDifferentReason)
    EditText mEdtDifferentReason;
    @ViewById(R.id.tvSubmit)
    TextView mtvSubmit;

    @AfterViews
    void afterView() {
        configRecycleView();
        setDefaultData();
        setAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null)
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.vacation_day));
    }

    @CheckedChange(R.id.cbDifferentReson)
    void checkedChanged(CompoundButton hello, boolean isChecked) {
        if (isChecked) {
            mEdtDifferentReason.setVisibility(View.VISIBLE);
        } else {
            mEdtDifferentReason.setVisibility(View.GONE);
        }
    }

    @Click(R.id.tvSubmit)
    void submitMail() {

    }

    private void configRecycleView() {
        mRecycleViewReasons.setLayoutManager(new MyLinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, true));
    }

    private void setAdapter() {
        mAdapter = new ReasonDayOffAdapter(getActivity(), mDatas, this);
        mRecycleViewReasons.setAdapter(mAdapter);
    }

    private void setDefaultData() {
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                Reason reason = new Reason(getString(R.string.test_reson), i);
                mDatas.add(reason);
            } else {
                Reason reason = new Reason(getString(R.string.test_reson_demo), i);
                mDatas.add(reason);
            }
        }
    }

    @Override
    public void onShowReason(Reason reasonChoosed, boolean isAdd) {
        if (isAdd) {
            mReasonChooseds.add(reasonChoosed);
        } else {
            if (mReasonChooseds.size() > 0) {
                for (int i = 0; i < mReasonChooseds.size(); i++) {
                    if (reasonChoosed.getId() == mReasonChooseds.get(i).getId()) {
                        mReasonChooseds.remove(i);
                    }
                }
            }
        }
        Toast.makeText(getActivity(), "size is " + mReasonChooseds.size(), Toast.LENGTH_LONG).show();
    }
}