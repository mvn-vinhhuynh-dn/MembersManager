package com.asiantech.membersmanager.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.asiantech.membersmanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/22/15.
 */
@EFragment(R.layout.dialog_change_pass)
public class ChangePassDialog extends DialogFragment {
    @ViewById(R.id.ll_new_pass)
    LinearLayout mLnNewPass;

    @ViewById(R.id.llConfirm_pass)
    LinearLayout mLnConfirmPass;

    @ViewById(R.id.llOldPass)
    LinearLayout mLnOldPass;

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

    @Click(R.id.img_close_dialog_change_pass)
    void closeDialog() {
        if (getActivity() != null && getDialog().isShowing()) {
            this.dismiss();
        }
    }

    private void setFocusBackGroud(LinearLayout linearLayout) {
        linearLayout.setBackground(ContextCompat
                .getDrawable(getActivity(), R.drawable.bg_linearlayout_focus));
    }

    private void setDefaultBackGround(LinearLayout linearLayout) {
        linearLayout.setBackgroundColor(ContextCompat
                .getColor(getActivity(), R.color.linearlayout_background_edittext));
    }

    @FocusChange({R.id.edt_new_pass, R.id.edt_old_pass,
            R.id.edt_confirm_pass})
    void focusChangedEdtEmail(View hello, boolean hasFocus) {
        if (hasFocus) {
            switch (hello.getId()) {
                case R.id.edt_new_pass:
                    setFocusBackGroud(mLnNewPass);
                    break;
                case R.id.edt_old_pass:
                    setFocusBackGroud(mLnOldPass);
                    break;
                case R.id.edt_confirm_pass:
                    setFocusBackGroud(mLnConfirmPass);
                    break;
            }
        } else {
            switch (hello.getId()) {
                case R.id.edt_confirm_pass:
                    setDefaultBackGround(mLnConfirmPass);
                    break;
                case R.id.edt_old_pass:
                    setDefaultBackGround(mLnOldPass);
                    break;
                case R.id.edt_new_pass:
                    setDefaultBackGround(mLnNewPass);
                    break;
            }
        }
    }
}
