package com.asiantech.membersmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiantech.membersmanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/9/15.
 */
@EActivity(R.layout.activity_edit_profile)
public class EditProfileActivity extends AppCompatActivity {
    private ImageView mImageClose;
    private TextView mTvTitle;

    @ViewById(R.id.toolbar)
    Toolbar mToolBar;

    @AfterViews
    void afterView() {
        initView();
        initData();
    }

    private void initView() {
        mImageClose = (ImageView) mToolBar.findViewById(R.id.img_right);
        mTvTitle = (TextView) mToolBar.findViewById(R.id.tv_title);
    }

    private void initData() {
        mImageClose.setImageResource(R.drawable.ic_close_white);
        mTvTitle.setText(getString(R.string.edt_profile));
    }

    @Click(R.id.img_right)
    void closeEdit() {
        finish();
    }
}
