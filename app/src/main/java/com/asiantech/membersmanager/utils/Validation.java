package com.asiantech.membersmanager.utils;

import android.content.Context;
import android.widget.Toast;

import com.asiantech.membersmanager.R;

import java.util.regex.Pattern;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
public class Validation {
    public static final Pattern EMAIL_ASIANTECH = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@asiantech.vn");

    public static boolean isEmpty(String string, Context context) {
        if (string.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.error_field_required), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isEmail(String email, Context context) {
        if (email.isEmpty() || !Validation.EMAIL_ASIANTECH.matcher(email).matches()) {
            Toast.makeText(context, context.getString(R.string.error_invalid_email), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isPassword(String password, Context context) {
        if (password.isEmpty() || password.length() < 6) {
            Toast.makeText(context, context.getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isConfirmPass(String password, String passwordConfirm, Context context) {
        if (!passwordConfirm.equals(password)) {
            Toast.makeText(context, context.getString(R.string.error_invalid_confirm_password), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
