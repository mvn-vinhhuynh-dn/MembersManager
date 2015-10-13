package com.asiantech.membersmanager.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;

import com.asiantech.membersmanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.io.File;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/12/15.
 */
@EFragment(R.layout.dialog_choose_photo)
public class DialogChooseImage extends DialogFragment {
    public static final int CAMERA_REQUEST = 1888;
    public static final int GALLERY_REQUEST = 1999;

    @AfterViews
    void afterView() {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogShowAnimation;
        }
    }

    @Click(R.id.txt_btn_take_photo)
    void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photoFile = new File(Environment.getExternalStorageDirectory(),  "Photo.png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photoFile));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Click(R.id.txt_btn_choose_photo)
    void choosePhoto() {

    }

    @Click(R.id.txt_btn_cancel)
    void cancelDialog() {
        if (getDialog() != null) {
            this.dismiss();
        }
    }
}