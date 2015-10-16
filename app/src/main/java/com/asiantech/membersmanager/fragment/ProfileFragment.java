package com.asiantech.membersmanager.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.dialog.DialogChooseImage;
import com.asiantech.membersmanager.dialog.DialogChooseImage_;

import org.androidannotations.annotations.EFragment;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends BaseFragment {

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.my_profile));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_EDIT);
        }
    }

//    public void clickEdit() {
//        updateViewEdit();
//        if (mOnBaseFragmentListener != null) {
//            mOnBaseFragmentListener.setTitleHeader(getString(R.string.edit_profile));
//            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DONE);
//            isEditing = true;
//        }
//        postData();
//    }
//
//    public void clickDone() {
//        showProfileInfor();
//        if (mOnBaseFragmentListener != null) {
//            mOnBaseFragmentListener.setTitleHeader(getString(R.string.my_profile));
//            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_EDIT);
//            isEditing = false;
//        }
//        reloadData();
//    }

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
//        mEdtName.setVisibility(View.VISIBLE);
//        mEdtCity.setVisibility(View.VISIBLE);
//        mEdtCountry.setVisibility(View.VISIBLE);
//        mEdtDob.setVisibility(View.VISIBLE);
//        mEdtGitHupMail.setVisibility(View.VISIBLE);
//        mEdtHomePhone.setVisibility(View.VISIBLE);
//        mEdtMailAddress.setVisibility(View.VISIBLE);
//        mEdtPhoneNum.setVisibility(View.VISIBLE);
//        mEdtPosition.setVisibility(View.VISIBLE);
//        mImgEditAvatar.setVisibility(View.VISIBLE);
//        // Set text for edit text
//        mEdtName.setText(mTvName.getText());
//        mEdtCity.setText(mTvCity.getText());
//        mEdtCountry.setText(mTvCountry.getText());
//        mEdtDob.setText(mTvDob.getText());
//        mEdtGitHupMail.setText(mTvGitHupMail.getText());
//        mEdtHomePhone.setText(mTvHomePhone.getText());
//        mEdtMailAddress.setText(mTvMailAddress.getText());
//        mEdtPhoneNum.setText(mTvPhoneNum.getText());
//        mEdtPosition.setText(mTvPosition.getText());
//        // clear text of text view
//        mTvCity.setText("");
//        mTvPosition.setText("");
//        mTvPhoneNum.setText("");
//        mTvName.setText("");
//        mTvMailAddress.setText("");
//        mTvCountry.setText("");
//        mTvDob.setText("");
//        mTvGitHupMail.setText("");
//        mTvHomePhone.setText("");
    }

    private void showProfileInfor() {
//        mEdtCity.setVisibility(View.GONE);
//        mEdtCountry.setVisibility(View.GONE);
//        mEdtDob.setVisibility(View.GONE);
//        mEdtGitHupMail.setVisibility(View.GONE);
//        mEdtHomePhone.setVisibility(View.GONE);
//        mEdtMailAddress.setVisibility(View.GONE);
//        mEdtName.setVisibility(View.GONE);
//        mEdtPhoneNum.setVisibility(View.GONE);
//        mEdtPosition.setVisibility(View.GONE);
//
//        mImgEditAvatar.setVisibility(View.GONE);
//        // set new data
//        mTvCity.setText(mEdtCity.getText());
//        mTvPosition.setText(mEdtPosition.getText());
//        mTvPhoneNum.setText(mEdtPhoneNum.getText());
//        mTvName.setText(mEdtName.getText());
//        mTvMailAddress.setText(mEdtMailAddress.getText());
//        mTvCountry.setText(mEdtCountry.getText());
//        mTvDob.setText(mEdtDob.getText());
//        mTvGitHupMail.setText(mEdtGitHupMail.getText());
//        mTvHomePhone.setText(mEdtHomePhone.getText());
    }

    private void setFocusBackgroud(EditText editText) {
        editText.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.bg_edit_text_register_focus));
    }

    private void setDefaultBackgroud(EditText editText) {
        editText.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.bg_edt_edit_profile));
    }

//    @Click(R.id.img_edit_avata)
//    void editAvatar() {
//        showDialogChooseImage();
//    }
//
//    @FocusChange({R.id.edt_name, R.id.edt_home_phone, R.id.edt_position,
//            R.id.edt_mail_address, R.id.edt_githup_mail, R.id.edt_city,
//            R.id.edt_country, R.id.edt_dob, R.id.edt_fone_num})
//    void focusChangedEdtEmail(View hello, boolean hasFocus) {
//        if (hasFocus) {
//            switch (hello.getId()) {
//                case R.id.edt_name:
//                    setFocusBackgroud(mEdtName);
//                    break;
//                case R.id.edt_home_phone:
//                    setFocusBackgroud(mEdtHomePhone);
//                    break;
//                case R.id.edt_fone_num:
//                    setFocusBackgroud(mEdtPhoneNum);
//                    break;
//                case R.id.edt_dob:
//                    setFocusBackgroud(mEdtDob);
//                    break;
//                case R.id.edt_country:
//                    setFocusBackgroud(mEdtCountry);
//                    break;
//                case R.id.edt_city:
//                    setFocusBackgroud(mEdtCity);
//                    break;
//                case R.id.edt_githup_mail:
//                    setFocusBackgroud(mEdtGitHupMail);
//                    break;
//                case R.id.edt_mail_address:
//                    setFocusBackgroud(mEdtMailAddress);
//                    break;
//                case R.id.edt_position:
//                    setFocusBackgroud(mEdtPosition);
//                    break;
//            }
//        } else {
//            switch (hello.getId()) {
//                case R.id.edt_name:
//                    setDefaultBackgroud(mEdtName);
//                    break;
//                case R.id.edt_home_phone:
//                    setDefaultBackgroud(mEdtHomePhone);
//                    break;
//                case R.id.edt_fone_num:
//                    setDefaultBackgroud(mEdtPhoneNum);
//                    break;
//                case R.id.edt_dob:
//                    setDefaultBackgroud(mEdtDob);
//                    break;
//                case R.id.edt_country:
//                    setDefaultBackgroud(mEdtCountry);
//                    break;
//                case R.id.edt_city:
//                    setDefaultBackgroud(mEdtCity);
//                    break;
//                case R.id.edt_githup_mail:
//                    setDefaultBackgroud(mEdtGitHupMail);
//                    break;
//                case R.id.edt_mail_address:
//                    setDefaultBackgroud(mEdtMailAddress);
//                    break;
//                case R.id.edt_position:
//                    setDefaultBackgroud(mEdtPosition);
//                    break;
//            }
//        }
//    }
}