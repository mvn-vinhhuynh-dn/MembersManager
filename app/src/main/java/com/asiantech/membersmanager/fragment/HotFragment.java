package com.asiantech.membersmanager.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.HomeAdapter;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by xuanphu on 15/10/2015.
 */
@EFragment(R.layout.fragment_notification_hot)
public class HotFragment extends BaseFragment implements CallDetailItem {
    @ViewById(R.id.recyclerHot)
    RecyclerView mRecyclerHot;
    @ViewById(R.id.swipeRefreshLayoutHot)
    SwipeRefreshLayout mSwipeRefreshLayoutHot;
    private HomeAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @FragmentArg
    ArrayList<Notification> mNotifications;

    @AfterViews
    void afterViews(){
        mAdapter = new HomeAdapter(getActivity(), mNotifications, this);
        // Config recycleview
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerHot.setLayoutManager(mLinearLayoutManager);
        mRecyclerHot.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        final ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(500);
        scaleAdapter.setFirstOnly(false);
        mSwipeRefreshLayoutHot.setEnabled(true);
        // setAdapter
        mRecyclerHot.setAdapter(scaleAdapter);
        mSwipeRefreshLayoutHot.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayoutHot.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshItems();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Notification notification = new Notification();
                        notification.setIsFavorite(false);
                        notification.setIsHot(true);
                        notification.setIsRead(false);
                        notification.setMAvata(R.drawable.p2);
                        notification.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                                "Để những dấu yêu sẽ không phai mờ\\n\"");
                        notification.setMSender("Le Thai Son");
                        notification.setMTittle("Thong bao hop khan cap");
                        notification.setMTime("14:32 PM, 06/10");
                        mNotifications.add(notification);
                        scaleAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayoutHot.setRefreshing(false);
                    }

                }, 2000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.hot));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
            for (int i= 0 ; i< mNotifications.size();i++){
                Log.d("----> hhot  ", mNotifications.get(i).getIsRead()+"");
            }
        }
    }

    @Override
    public void OnCallDetails(ArrayList<Notification> arrayList, int position) {
        mNotifications.get(position).setIsRead(true);
        Log.d("----> hhot  ", position +"");
        for (int i= 0 ; i< mNotifications.size();i++){
            Log.d("----> hhot --- ", mNotifications.get(i).getIsRead()+"");
        }
        DetailHotNotificationFragment detailHotNotificationFragment = DetailHotNotificationFragment_.builder()
                .mNotifications(arrayList)
                .mPosition(position)
                .build();
        replaceFragment(detailHotNotificationFragment, false);
    }
}
