package com.asiantech.membersmanager.dialog;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.utils.Validation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 12-03-2015.
 */

@EFragment(R.layout.fragment_dialog_register)
public class DialogRegisterFragment extends DialogFragment {

    @ViewById(R.id.spnTeamGroupSignUp)
    Spinner mSpnTeamGroupSignUp;

    @StringArrayRes(R.array.array_team)
    String[] mArrayTeams;

    @ViewById(R.id.etUserNameSignUp)
    EditText mEdtUserNameSignUp;

    @ViewById(R.id.etEmailSignUp)
    EditText mEdtEmailSignUp;

    @ViewById(R.id.etPasswordSignUp)
    EditText mEdtPasswordSignUp;

    @ViewById(R.id.etConfirmPasswordSignUp)
    EditText mEdtConfirmPasswordSignUp;

    @ViewById(R.id.pbLoadingRegister)
    ProgressBar mPbLoadingRegister;

    @ViewById(R.id.btnRegister)
    Button mBtnRegister;

    @ViewById(R.id.llUserNameSignUp)
    LinearLayout mlnUserNameSignUp;

    @ViewById(R.id.llConfirmPasswordSignUp)
    LinearLayout mlnConfirmPassSignUp;

    @ViewById(R.id.llEmailSignUp)
    LinearLayout mlnEmailSignUp;

    @ViewById(R.id.llPasswordSignUp)
    LinearLayout mlnPassWordSignUp;

    @AfterViews
    void initDialogRegister() {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow()
                    .setBackgroundDrawableResource(android.R.color.transparent);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogShowAnimation;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.item_spinner, mArrayTeams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnTeamGroupSignUp.setAdapter(adapter);
    }

    @Click(R.id.btnRegister)
    void initClickRegister() {
        String userName = mEdtUserNameSignUp.getText().toString();
        String email = mEdtEmailSignUp.getText().toString();
        String password = mEdtPasswordSignUp.getText().toString();
        String confirmPass = mEdtConfirmPasswordSignUp.getText().toString();

        // Validation edit text form register
        if (Validation.isEmpty(userName, getActivity())) {
            mEdtUserNameSignUp.requestFocus();
        } else if (Validation.isEmail(email, getActivity())) {
            mEdtEmailSignUp.requestFocus();
        } else if (Validation.isPassword(password, getActivity())) {
            mEdtPasswordSignUp.requestFocus();
        } else if (Validation.isConfirmPass(password, confirmPass, getActivity())) {
            mEdtConfirmPasswordSignUp.requestFocus();
        } else {
            // Do Register Function
        }
    }

    @Click(R.id.img_close_dialog)
    void closeDialog() {
        if (getActivity() != null && getDialog().isShowing()) {
            this.dismiss();
        }
    }

    @FocusChange({R.id.etEmailSignUp})
    void focusChangedEdtEmail(View hello, boolean hasFocus) {
        if (hasFocus) {
            mlnEmailSignUp.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.bg_edit_text_register_focus));
        } else {
            mlnEmailSignUp.setBackground(getResources()
                    .getDrawable(R.drawable.bg_edit_text_normal));
        }
    }

    @FocusChange({R.id.etUserNameSignUp})
    void focusChangedEdtUsername(View hello, boolean hasFocus) {
        if (hasFocus) {
            mlnUserNameSignUp.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.bg_edit_text_register_focus));
        } else {
            mlnUserNameSignUp.setBackground(getResources()
                    .getDrawable(R.drawable.bg_edit_text_normal));
        }
    }

    @FocusChange({R.id.etPasswordSignUp})
    void focusChangedEdtPassWord(View hello, boolean hasFocus) {
        if (hasFocus) {
            mlnPassWordSignUp.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.bg_edit_text_register_focus));
        } else {
            mlnPassWordSignUp.setBackground(getResources()
                    .getDrawable(R.drawable.bg_edit_text_normal));
        }
    }

    @FocusChange({R.id.etConfirmPasswordSignUp})
    void focusChangedEdtConfirmPass(View hello, boolean hasFocus) {
        if (hasFocus) {
            mlnConfirmPassSignUp.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.bg_edit_text_register_focus));
        } else {
            mlnConfirmPassSignUp.setBackground(getResources()
                    .getDrawable(R.drawable.bg_edit_text_normal));
        }
    }
}