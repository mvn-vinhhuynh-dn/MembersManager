package com.asiantech.membersmanager.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.models.Notification;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.*;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_detail_notification)
public class NotificationDetailFragment extends BaseFragment {

    @ViewById(R.id.imgAvataDetail)
    ImageView imgAvataDetail;
    @ViewById(R.id.tvTittleDetails)
    TextView tvTittleDetails;
    @ViewById(R.id.tvSenderDetail)
    TextView tvSenderDetail;
    @ViewById(R.id.tvTimeDetail)
    TextView tvTimeDetail;
    @ViewById(R.id.tvContentDetail)
    TextView tvContentDetail;
    @ViewById(R.id.rlXemchitiet)
    RelativeLayout rlXemchitiet;
    @ViewById(R.id.tvXemchitiet)
    TextView tvXemchitiet;
    @ViewById(R.id.tvAnxemchitiet)
    TextView tvAnxemchitiet;
    @ViewById(R.id.imgFavoriteDetail)
    ImageView imgFavoriteDetail;

    @FragmentArg
    Notification notification;

    @AfterViews
    void afterViews(){
        tvTittleDetails.setText(notification.getMTittle());
        imgAvataDetail.setImageResource(notification.getMAvata());
        tvSenderDetail.setText(notification.getMSender());
        tvTimeDetail.setText(notification.getMTime());
        tvContentDetail.setText(notification.getMContent());
    }

    @Click(R.id.tvXemchitiet)
    void xemchitiet(){
        tvXemchitiet.setVisibility(View.INVISIBLE);
        tvAnxemchitiet.setVisibility(View.VISIBLE);
        rlXemchitiet.setVisibility(View.VISIBLE);
    }
    @Click(R.id.tvAnxemchitiet)
    void anxemchitiet(){
        tvXemchitiet.setVisibility(View.VISIBLE);
        tvAnxemchitiet.setVisibility(View.INVISIBLE);
        rlXemchitiet.setVisibility(View.GONE);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null)
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_detail));
    }
}
