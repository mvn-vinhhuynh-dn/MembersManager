package com.asiantech.membersmanager.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.dialog.DialogChooseImage;
import com.asiantech.membersmanager.dialog.DialogChooseImage_;
import com.asiantech.membersmanager.views.CircleImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends BaseFragment implements OnDateSetListener {
    @ViewById(R.id.tv_name_profile)
    TextView mtvName;

    @ViewById(R.id.tv_day_off_profile)
    TextView mtvDayOff;

    @ViewById(R.id.tv_dob_profile)
    TextView mtvDob;

    @ViewById(R.id.tv_git_profile)
    TextView mtvGitHub;

    @ViewById(R.id.tv_mail_profile)
    TextView mtvEmail;

    @ViewById(R.id.tv_phone_profile)
    TextView mtvPhoneNum;

    @ViewById(R.id.tv_pos_profile)
    TextView mtvPosition;

    @ViewById(R.id.edt_dob_profile)
    EditText mEdtDob;

    @ViewById(R.id.edt_name_profile)
    EditText mEdtName;

    @ViewById(R.id.edt_phone_profile)
    EditText mEdtPhoneNum;

    @ViewById(R.id.img_avatar)
    CircleImageView mCirImgAvatar;

    @ViewById(R.id.img_profile_edit)
    ImageView mImgEditAvatar;

    public boolean isEditing = false;
    private DatePickerDialog mDatePickerDialog;

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null && !isEditing) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.my_profile));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_EDIT);
        }
    }

    public void clickEdit() {
        updateViewEdit();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.edit_profile));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DONE);
            isEditing = true;
        }
        postData();
    }

    public void clickDone() {
        showProfileInfo();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.my_profile));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_EDIT);
            isEditing = false;
        }
        reloadData();
    }

    private void showDialogChooseImage() {
        DialogChooseImage dialogChooseImage = new DialogChooseImage_();
        FragmentManager fmRegister = getActivity().getSupportFragmentManager();
        FragmentTransaction ftChooseImage = fmRegister.beginTransaction();
        dialogChooseImage.show(ftChooseImage, "ChooseImage Dialog");
    }

    private void reloadData() {
        //TOdo reloadata
    }

    private void postData() {
        //TOdo postdata
    }

    private void updateViewEdit() {
        // Show views can edit
        mEdtName.setVisibility(View.VISIBLE);
        mEdtDob.setVisibility(View.VISIBLE);
        mEdtPhoneNum.setVisibility(View.VISIBLE);
        // Set text for edit text
        mEdtName.setText(mtvName.getText());
        mEdtDob.setText(mtvDob.getText());
        mEdtPhoneNum.setText(mtvPhoneNum.getText());
        // clear text of text view
        mtvDob.setText("");
        mtvPhoneNum.setText("");
        mtvName.setText("");

        mImgEditAvatar.setVisibility(View.VISIBLE);
    }

    @AfterViews
    void afterView() {
        initData();
        initListener();
        initView();
    }

    private void initListener() {
        mDatePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                setDefaultBackgroud(mEdtDob);
                Log.d("vvvv", "cancel");
            }
        });
    }

    private void initView() {
        mEdtDob.setCursorVisible(false);
        mEdtDob.setFocusableInTouchMode(false);
    }

    private void showProfileInfo() {
        mEdtDob.setVisibility(View.GONE);
        mEdtPhoneNum.setVisibility(View.GONE);
        mEdtName.setVisibility(View.GONE);
        // set new data
        mtvPhoneNum.setText(mEdtPhoneNum.getText());
        mtvName.setText(mEdtName.getText());
        mtvDob.setText(mEdtDob.getText());

        mImgEditAvatar.setVisibility(View.GONE);

    }

    private void setFocusBackgroud(EditText editText) {
        editText.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.bg_edit_text_register_focus));
    }

    private void setDefaultBackgroud(EditText editText) {
        editText.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.bg_edt_edit_profile));
    }

    @Click(R.id.img_profile_edit)
    void editAvatar() {
        showDialogChooseImage();
    }

    private void initData() {
        Calendar now = Calendar.getInstance();
        mDatePickerDialog = newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)

        );
        mDatePickerDialog.setMinDate(now);
    }

    private void showDialogChooseDay() {

        if (!mDatePickerDialog.isHidden()) {
            Log.d("vvvvv", "show dialog");
            mDatePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog_birthday");
        }
        setFocusBackgroud(mEdtDob);
    }

    @Click(R.id.edt_dob_profile)
    void chooseBirthday() {
        showDialogChooseDay();
    }

    @FocusChange({R.id.edt_phone_profile, R.id.edt_pos_profile, R.id.edt_name_profile,
            R.id.edt_mail_profile, R.id.edt_git_profile, R.id.edt_dob_profile})
    void focusChangedEdtEmail(View hello, boolean hasFocus) {
        if (hasFocus) {
            switch (hello.getId()) {
                case R.id.edt_name_profile:
                    setFocusBackgroud(mEdtName);
                    break;
                case R.id.edt_phone_profile:
                    setFocusBackgroud(mEdtPhoneNum);
                    break;
                case R.id.edt_dob_profile:
                    showDialogChooseDay();
                    break;
            }
        } else {
            switch (hello.getId()) {
                case R.id.edt_name_profile:
                    setDefaultBackgroud(mEdtName);
                    break;
                case R.id.edt_phone_profile:
                    setDefaultBackgroud(mEdtPhoneNum);
                    break;
                case R.id.edt_dob_profile:
                    setDefaultBackgroud(mEdtDob);
                    break;
            }
        }
    }

    public void setNewAvatar(Uri uri, Bitmap bitmap, boolean isTake) {
        if (!isTake) {
            mCirImgAvatar.setImageURI(uri);
        } else {
            // Scale bitmap
            int widthScale = (int) getResources().getDimension(R.dimen.width_100dp);
            int heightScale = bitmap.getHeight() * widthScale / bitmap.getWidth();
            Bitmap bitmapScaled = Bitmap.createScaledBitmap(
                    bitmap, widthScale, heightScale, false);

            mCirImgAvatar.setImageBitmap(bitmapScaled);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mEdtDob.setText(date);
        setDefaultBackgroud(mEdtDob);
    }
}