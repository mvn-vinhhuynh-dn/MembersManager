package com.asiantech.membersmanager.dialog;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;

import com.asiantech.membersmanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/12/15.
 */
@EFragment(R.layout.dialog_choose_photo)
public class DialogChooseImage extends DialogFragment {
    public static final int SELECT_PHOTO = 1111;
    public static final int TAKE_PICTURE = 2222;
    private Uri mUri;


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
    void takePhotoFromCamera() {
        takePhoto();
    }

    @Click(R.id.txt_btn_choose_photo)
    void choosePhoto() {
        selectPhoto();
    }

    @Click(R.id.txt_btn_cancel)
    void cancelDialog() {
        if (getDialog() != null) {
            this.dismiss();
        }
    }

    public void selectPhoto() {
        Intent photoPickerIntent = new Intent();
        photoPickerIntent.setType("image/*");
        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, TAKE_PICTURE);
    }
}