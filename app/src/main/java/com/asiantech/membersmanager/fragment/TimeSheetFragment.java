package com.asiantech.membersmanager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.TimeSheetAdapter;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_timesheet)
public class TimeSheetFragment extends BaseFragment implements CallDetailItem {
    private ArrayList<Notification> mListNotifications = new ArrayList<>();
    private TimeSheetAdapter mAdapter;

    @ViewById(R.id.recycler_timeSheet)
    RecyclerView mRecycleTimeSheet;

    public TimeSheetFragment(){
        fakeData();
    }
    @AfterViews
    public void afterView() {
        initView();
        initData();
        setAdapter();
    }

    private void initData() {
        mRecycleTimeSheet.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    private void initView() {
    }

    private void setAdapter() {
        mAdapter = new TimeSheetAdapter(mListNotifications, getActivity(), this);
        mRecycleTimeSheet.addItemDecoration(new DividerItemDecoration(getResources()
                .getDrawable(R.drawable.divider)));
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(500);
        scaleAdapter.setFirstOnly(true);
        mRecycleTimeSheet.setAdapter(scaleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.time_sheet));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }

    private void fakeData() {
        for (int i = 0; i < 10; i++) {
            Notification notification = new Notification();
            notification.setIsFavorite(true);
            notification.setIsHot(false);
            notification.setIsRead(false);
            notification.setMAvata(R.drawable.p1);
            notification.setMContent("Đã có lúc anh mong tim mình bé lại. Để nỗi nhớ em không thể " +
                    "nào thêm nữa. Đã có lúc anh mong ngừng thời gian trôi" +
                    ". Để những dấu yêu sẽ không phai mờ");
            notification.setMSender("Le Thai Son");
            notification.setMTittle("Thong bao hop khan cap");
            notification.setMTime("14:32 PM, 06/10");
            mListNotifications.add(notification);
        }
    }

    @Override
    public void OnCallDetails(ArrayList<Notification> arrayList, int position) {
        arrayList.get(position).setIsRead(true);
        NotificationDetailFragment notificationDetailFragment = NotificationDetailFragment_
                .builder()
                .mNotifications(arrayList)
                .mPosition(position)
                .build();
        replaceFragment(notificationDetailFragment, false);
    }
}