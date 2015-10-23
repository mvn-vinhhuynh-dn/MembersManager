package com.asiantech.membersmanager.fragment;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_vacation)
public class VacationDayFragment extends BaseFragment implements OnDateSetListener {

    @ViewById(R.id.cbDifferentReson)
    RadioButton mCbChooseDifferentReason;

    @ViewById(R.id.edtDifferentReason)
    EditText mEdtDifferentReason;

    @ViewById(R.id.tv_from_day)
    TextView mtvFromDay;

    @ViewById(R.id.tv_to_day)
    TextView mtvToDay;

    private boolean isToDay = false;
    private boolean isFromDay = false;

    @AfterViews
    void afterView() {
        setDefaultDate();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.vacation_day));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_SENT);
        }
    }

    @CheckedChange(R.id.cbDifferentReson)
    void checkedChangedCheckBox( boolean isChecked) {
        if (isChecked) {
            mEdtDifferentReason.setVisibility(View.VISIBLE);
        } else {
            mEdtDifferentReason.setVisibility(View.GONE);
        }
    }

    @CheckedChange({R.id.rdChoose_one, R.id.rdChoose_two,
            R.id.rdChoose_three, R.id.rdChoose_four, R.id.rdChoose_five})
    void checkChangeRadioButton(CompoundButton hello, boolean isChecked) {
        if (isChecked) {
            switch (hello.getId()) {
                case R.id.rdChoose_one:
                    break;
                case R.id.rdChoose_two:
                    break;
                case R.id.rdChoose_three:
                    break;
                case R.id.rdChoose_four:
                    break;
                case R.id.rdChoose_five:
                    break;
            }
        }
    }

    public void clickSentMail() {
        Toast.makeText(getActivity(), "sent", Toast.LENGTH_SHORT).show();
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
        dpd.setMinDate(now);
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog_vacationday");
    }

    private void setDefaultDate() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/"
                + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        mtvToDay.setText(currentDate);
        mtvFromDay.setText(currentDate);
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