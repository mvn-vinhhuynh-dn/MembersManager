package com.asiantech.membersmanager.dialog;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.utils.Validation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by tamnm on 8/16/15.
 */
@EFragment(R.layout.fragment_dialog_forgot)
public class DialogForgotFragment extends DialogFragment {
    @ViewById(R.id.llEmailForgot)
    LinearLayout mlnEmail;

    @ViewById(R.id.etEmailForgot)
    EditText mEdtEmailForgot;

    @AfterViews
    void initDialogForgot() {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogShowAnimation;
        }
    }

    @FocusChange({R.id.etEmailForgot})
    void focusEdtEmail(View hello, boolean hasFocus) {
        if (hasFocus) {
            mlnEmail.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.bg_edit_text_register_focus));
        } else {
            mlnEmail.setBackground(getResources()
                    .getDrawable(R.drawable.bg_edit_text_normal));
        }
    }

    @Click(R.id.btnResetPwd)
    void initClickResetPwd() {
        String email = mEdtEmailForgot.getText().toString();
        // Validation edit text form forgot password
        if (Validation.isEmail(email, getActivity())) {
            mEdtEmailForgot.requestFocus();
        }
    }

    @Click(R.id.img_close_dialog_forgot)
    void closeDialog() {
        if (getActivity() != null && getDialog().isShowing()) {
            this.dismiss();
        }
    }
}
