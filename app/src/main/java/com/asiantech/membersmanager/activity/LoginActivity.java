package com.asiantech.membersmanager.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.MainActivity_;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.dialog.DialogRegisterFragment;
import com.asiantech.membersmanager.dialog.DialogRegisterFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EActivity(R.layout.login_activity)
public class LoginActivity extends AppCompatActivity {
    private Handler mHandler;
    private Handler mHandlerShowLogo;
    private Runnable mRunable;
    @AnimationRes(R.anim.translate_left)
    Animation mAnimLeft;

    @AnimationRes(R.anim.translate_right)
    Animation mAnimRight;

    @AnimationRes(R.anim.translate_down)
    Animation mAnimDown;

    @AnimationRes(R.anim.translate_up)
    Animation mAnimUp;

    @AnimationRes(R.anim.translate_logo)
    Animation mAnimLogo;

    @ViewById(R.id.imgAsian)
    ImageView mImgAsian;

    @ViewById(R.id.imgTech)
    ImageView mImgTech;

    @ViewById(R.id.llImgLogo)
    LinearLayout mLlImgLogo;

    @ViewById(R.id.btnForgot)
    Button mBtnForgot;

    @ViewById(R.id.btnSignUp)
    Button mBtnSignUp;

    @ViewById(R.id.etEmailSignIn)
    EditText mEtEmailSignIn;

    @ViewById(R.id.etPasswordSignIn)
    EditText mEtPasswordSignIn;

    @ViewById(R.id.tvWelcome)
    TextView mTvWelcome;

    @ViewById(R.id.btnSignIn)
    Button mBtnSignIn;

    @ViewById(R.id.llEmail)
    LinearLayout mLlEmail;

    @ViewById(R.id.llPassword)
    LinearLayout mLlPassword;

    @ViewById(R.id.rlSignIn)
    RelativeLayout mRlSignIn;

    @ViewById(R.id.pbLoadingSignIn)
    ProgressBar mPbLoadingSignIn;

    @AfterViews
    public void afterView() {
        setDelayStartAnim();
    }

    private void setDelayStartAnim() {
        mHandlerShowLogo = new Handler();
        mRunable = new Runnable() {
            @Override
            public void run() {
                mLlImgLogo.setVisibility(View.VISIBLE);
                startAnimation();
            }
        };
        mHandlerShowLogo.postDelayed(mRunable, 1000);
    }

    private void startAnimation() {
        if (mHandlerShowLogo != null && mRunable != null) {
            mHandlerShowLogo.removeCallbacks(mRunable);
        }
        ColorStateList colorStateList = getResources().getColorStateList(R.color.button_signup_forgot);
        mBtnSignUp.setTextColor(colorStateList);
        mBtnForgot.setTextColor(colorStateList);
        mImgAsian.startAnimation(mAnimLeft);
        mImgTech.startAnimation(mAnimRight);
        mHandler = new Handler(callback);
        // Send handler message
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                Bundle send = new Bundle();
                send.putInt("message", 1);
                msg.setData(send);
                mHandler.sendMessage(msg);
            }
        }, 2000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                Bundle send = new Bundle();
                send.putInt("message", 2);
                msg.setData(send);
                mHandler.sendMessage(msg);
            }
        }, 2000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                Bundle send = new Bundle();
                send.putInt("message", 3);
                msg.setData(send);
                mHandler.sendMessage(msg);
            }
        }, 2500);
    }

    @Click(R.id.btnSignIn)
    void clickSignIn() {
        MainActivity_.intent(LoginActivity.this).start();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        finish();
    }

    @Click(R.id.btnSignUp)
    void clickSignUp() {
        DialogRegisterFragment dialogRegister = new DialogRegisterFragment_();
        FragmentManager fmRegister = getSupportFragmentManager();
        FragmentTransaction ftRegister = fmRegister.beginTransaction();
        dialogRegister.show(ftRegister, "Register Fragment");
    }

    // Init handler message
    private final Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Bundle receive = msg.getData();
            int initMsg = receive.getInt("message");
            switch (initMsg) {
                case 1:
                    mLlImgLogo.startAnimation(mAnimLogo);
                    break;
                case 2:
                    mTvWelcome.setVisibility(View.VISIBLE);
                    mTvWelcome.startAnimation(mAnimDown);
                    break;
                case 3:
                    mLlEmail.setVisibility(View.VISIBLE);
                    mLlEmail.startAnimation(mAnimUp);
                    mLlPassword.setVisibility(View.VISIBLE);
                    mLlPassword.setAnimation(mAnimUp);
                    mRlSignIn.setVisibility(View.VISIBLE);
                    mRlSignIn.startAnimation(mAnimUp);
                    mBtnForgot.setVisibility(View.VISIBLE);
                    mBtnForgot.startAnimation(mAnimUp);
                    mBtnSignUp.setVisibility(View.VISIBLE);
                    mBtnSignUp.startAnimation(mAnimUp);
                    break;
            }
            return false;
        }
    };
}
