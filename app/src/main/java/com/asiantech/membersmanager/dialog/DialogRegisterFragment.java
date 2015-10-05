package com.asiantech.membersmanager.dialog;

import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.utils.Validation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by tamnm on 8/16/15.
 */
@EFragment(R.layout.fragment_dialog_register)
public class DialogRegisterFragment extends DialogFragment {

    @ViewById(R.id.spnTeamGroupSignUp)
    Spinner mSpnTeamGroupSignUp;

    @StringArrayRes(R.array.array_team)
    String[] mArrayTeam;

    @ViewById(R.id.etUserNameSignUp)
    EditText mEtUserNameSignUp;

    @ViewById(R.id.etEmailSignUp)
    EditText mEtEmailSignUp;

    @ViewById(R.id.etPasswordSignUp)
    EditText mEtPasswordSignUp;

    @ViewById(R.id.etConfirmPasswordSignUp)
    EditText mEtConfirmPasswordSignUp;

    @ViewById(R.id.pbLoadingRegister)
    ProgressBar mPbLoadingRegister;

    @ViewById(R.id.btnRegister)
    Button mBtnRegister;

    @AfterViews
    void initDialogRegister() {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, mArrayTeam);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnTeamGroupSignUp.setAdapter(adapter);
    }

    @Click(R.id.btnRegister)
    void initClickRegister() {
        String userName = mEtUserNameSignUp.getText().toString();
        String email = mEtEmailSignUp.getText().toString();
        String password = mEtPasswordSignUp.getText().toString();
        String confirmPass = mEtConfirmPasswordSignUp.getText().toString();

        // Validation edit text form register
        if (Validation.isEmpty(userName, getActivity())) {
            mEtUserNameSignUp.requestFocus();
        } else if (Validation.isEmail(email, getActivity())) {
            mEtEmailSignUp.requestFocus();
        } else if (Validation.isPassword(password, getActivity())) {
            mEtPasswordSignUp.requestFocus();
        } else if (Validation.isConfirmPass(password, confirmPass, getActivity())) {
            mEtConfirmPasswordSignUp.requestFocus();
        } else {
            // Do Register Function
        }
    }
}
