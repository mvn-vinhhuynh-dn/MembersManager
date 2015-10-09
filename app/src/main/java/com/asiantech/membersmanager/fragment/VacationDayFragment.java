package com.asiantech.membersmanager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.ReasonDayOffAdapter;
import com.asiantech.membersmanager.models.Reason;
import com.asiantech.membersmanager.utils.MyLinearLayoutManager;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_vacation)
public class VacationDayFragment extends BaseFragment implements
        ReasonDayOffAdapter.OnChooseReason,
        OnDateSetListener {

    private ReasonDayOffAdapter mAdapter;
    private ArrayList<Reason> mDatas = new ArrayList<>();
    private ArrayList<Reason> mReasonChooseds = new ArrayList<>();
    @ViewById(R.id.rcViewResons)
    RecyclerView mRecycleViewReasons;
    @ViewById(R.id.cbDifferentReson)
    CheckBox mCbChooseDifferentReason;
    @ViewById(R.id.edtDifferentReason)
    EditText mEdtDifferentReason;
    @ViewById(R.id.tvSubmit)
    TextView mtvSubmit;
    @ViewById(R.id.tv_from_day)
    TextView mtvFromDay;
    @ViewById(R.id.tv_to_day)
    TextView mtvToDay;
    private boolean isToDay = false;
    private boolean isFromDay = false;

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

    @Click(R.id.tv_to_day)
    void chooseToday() {
        isToDay = true;
        isFromDay = false;
        showDialogChooseDay();

    }

    @Click(R.id.tv_from_day)
    void chooseFromDay() {
        isToDay = false;
        isFromDay = true;
        showDialogChooseDay();
    }

    private void showDialogChooseDay() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    private void setDefaultDate() {
        Calendar calendar = Calendar.getInstance();
        String curentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        mtvToDay.setText(curentDate);
        mtvFromDay.setText(curentDate);
    }

    private void configRecycleView() {
        mRecycleViewReasons.setLayoutManager(new MyLinearLayoutManager(getActivity()
                .getBaseContext(), LinearLayoutManager.VERTICAL, true));
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
        setDefaultDate();
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
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        if (isToDay) {
            mtvToDay.setText(date);
        } else {
            mtvFromDay.setText(date);
        }
    }
}