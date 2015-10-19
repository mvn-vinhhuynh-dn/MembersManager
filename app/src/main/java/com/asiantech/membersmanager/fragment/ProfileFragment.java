package com.asiantech.membersmanager.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends BaseFragment {


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


    @ViewById(R.id.edt_day_off_profile)
    EditText mEdtDayOff;
    @ViewById(R.id.edt_dob_profile)
    EditText mEdtDob;
    @ViewById(R.id.edt_git_profile)
    EditText mEdtGitHub;
    @ViewById(R.id.edt_mail_profile)
    EditText mEdtEmail;
    @ViewById(R.id.edt_name_profile)
    EditText mEdtName;
    @ViewById(R.id.edt_phone_profile)
    EditText mEdtPhoneNum;
    @ViewById(R.id.edt_pos_profile)
    EditText mEdtPosition;

    @ViewById(R.id.img_avatar)
    CircleImageView mCirImgAvatar;
    @ViewById(R.id.img_profile_edit)
    ImageView mImgEditAvatar;

    public boolean isEditing = false;

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
        mEdtGitHub.setVisibility(View.VISIBLE);
        mEdtDob.setVisibility(View.VISIBLE);
        mEdtEmail.setVisibility(View.VISIBLE);
        mEdtPhoneNum.setVisibility(View.VISIBLE);
        mEdtPosition.setVisibility(View.VISIBLE);
        // Set text for edit text
        mEdtName.setText(mtvName.getText());
        mEdtGitHub.setText(mtvGitHub.getText());
        mEdtDob.setText(mtvDob.getText());
        mEdtEmail.setText(mtvEmail.getText());
        mEdtPhoneNum.setText(mtvPhoneNum.getText());
        mEdtPosition.setText(mtvPosition.getText());
        // clear text of text view
        mtvPosition.setText("");
        mtvPhoneNum.setText("");
        mtvEmail.setText("");
        mtvDob.setText("");
        mtvGitHub.setText("");
        mtvPhoneNum.setText("");
        mtvName.setText("");

        mImgEditAvatar.setVisibility(View.VISIBLE);

    }

    private void showProfileInfo() {

        mEdtDob.setVisibility(View.GONE);
        mEdtGitHub.setVisibility(View.GONE);
        mEdtPhoneNum.setVisibility(View.GONE);
        mEdtEmail.setVisibility(View.GONE);
        mEdtName.setVisibility(View.GONE);
        mEdtPosition.setVisibility(View.GONE);

        mImgEditAvatar.setVisibility(View.GONE);
        // set new data
        mtvPosition.setText(mEdtPosition.getText());
        mtvPhoneNum.setText(mEdtPhoneNum.getText());
        mtvName.setText(mEdtName.getText());
        mtvEmail.setText(mEdtEmail.getText());
        mtvDob.setText(mEdtDob.getText());
        mtvGitHub.setText(mEdtGitHub.getText());
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
                    setFocusBackgroud(mEdtDob);
                    break;
                case R.id.edt_git_profile:
                    setFocusBackgroud(mEdtGitHub);
                    break;
                case R.id.edt_mail_profile:
                    setFocusBackgroud(mEdtEmail);
                    break;
                case R.id.edt_pos_profile:
                    setFocusBackgroud(mEdtPosition);
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
                case R.id.edt_git_profile:
                    setDefaultBackgroud(mEdtGitHub);
                    break;
                case R.id.edt_mail_profile:
                    setDefaultBackgroud(mEdtEmail);
                    break;
                case R.id.edt_pos_profile:
                    setDefaultBackgroud(mEdtPosition);
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
}