package com.asiantech.membersmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.animations.FlipAnimator;
import com.asiantech.membersmanager.views.CircleImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/9/15.
 */
@EActivity(R.layout.activity_edit_profile)
public class EditProfileActivity extends AppCompatActivity {
    @AfterViews
    void afterView() {
        final Button buttonAnimate = (Button) findViewById(R.id.buttonAnimate);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        final CircleImageView imageViewFlip = (CircleImageView) findViewById(R.id.imageView2);

        final CircleImageView imageViewOriginal = (CircleImageView) findViewById(R.id.imageView1);

        buttonAnimate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FlipAnimator animator = new FlipAnimator(imageViewOriginal, imageViewFlip,
                        imageViewFlip.getWidth() / 2, imageViewFlip.getHeight() / 2);
                if (imageViewOriginal.getVisibility() == View.GONE) {
                    animator.reverse();
                }
                layout.startAnimation(animator);
            }
        });
    }

}
