package com.asiantech.membersmanager.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.HomeAdapter;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;
import com.asiantech.membersmanager.views.CircleImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements CallDetailItem {
    @ViewById(R.id.recyclerHome)
    RecyclerView mRecycleHome;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.imgAvataHeader)
    CircleImageView imgAvataHeader;

    @ViewById(R.id.tvSenderHeader)
    TextView tvSenderHeader;

    @ViewById(R.id.tvTittleHeader)
    TextView tvTittleHeader;

    @ViewById(R.id.tvContentHeader)
    TextView tvContentHeader;

    @ViewById(R.id.tvTimeHeader)
    TextView tvTimeHeader;

    @ViewById(R.id.swipeHeader)
    RelativeLayout swipeHeader;

    @ViewById(R.id.tvSumRead)
    TextView tvSumRead;

    private ArrayList<Notification> mArraylists;
    private ArrayList<Notification> mArraylistsHeader;
    private HomeAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public HomeFragment() {
        mArraylists = new ArrayList<>();
        mArraylistsHeader = new ArrayList<>();
        setDefaultData();
    }

    @AfterViews
    void afterView() {
        mAdapter = new HomeAdapter(getActivity(), mArraylists, this);
        // Config recycleview
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleHome.setLayoutManager(mLinearLayoutManager);
        mRecycleHome.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(500);
        scaleAdapter.setFirstOnly(false);
        mSwipeRefreshLayout.setEnabled(true);
        // setAdapter
        mRecycleHome.setAdapter(scaleAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshItems();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Notification notification = new Notification();
                        notification.setIsFavorite(true);
                        notification.setIsHot(false);
                        notification.setIsRead(false);
                        notification.setMAvata(R.drawable.p1);
                        notification.setMContent("Thong bao");
                        notification.setMSender("Le Thai Son");
                        notification.setMTittle("Thong bao hop khan cap");
                        notification.setMTime("14:32 PM, 06/10");
                        mArraylists.add(notification);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                }, 2000);
            }
        });
        mRecycleHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // add swipeRefresh listener
                int passVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (passVisibleItems == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    private void setNotificationHot() {
        int sumRead = 0;
        for (int i = 0; i < mArraylistsHeader.size(); i++) {
            if (!mArraylistsHeader.get(i).getIsRead()) {
                imgAvataHeader.setImageResource(mArraylistsHeader.get(i).getMAvata());
                tvSenderHeader.setText(mArraylistsHeader.get(i).getMSender());
                tvTittleHeader.setText(mArraylistsHeader.get(i).getMTittle());
                tvTimeHeader.setText(mArraylistsHeader.get(i).getMTime());
                tvContentHeader.setText(mArraylistsHeader.get(i).getMContent());
                swipeHeader.setBackgroundDrawable(mContext
                        .getResources().getDrawable(R.drawable.shadow_view));
                break;
            }
        }
        for (int i = 0; i < mArraylistsHeader.size(); i++) {
            if (!mArraylistsHeader.get(i).getIsRead()) {
                sumRead = sumRead + 1;
            }
        }
        if (sumRead == 0) {
            imgAvataHeader.setImageResource(mArraylistsHeader.get(0).getMAvata());
            tvSenderHeader.setText(mArraylistsHeader.get(0).getMSender());
            tvTittleHeader.setText(mArraylistsHeader.get(0).getMTittle());
            tvTimeHeader.setText(mArraylistsHeader.get(0).getMTime());
            tvContentHeader.setText(mArraylistsHeader.get(0).getMContent());
            swipeHeader.setBackgroundColor(mContext
                    .getResources().getColor(R.color.white));
        }
        tvSumRead.setText("Còn " + sumRead + " tin...");
    }

    @Click(R.id.swipeHeader)
    void clickItem() {
        HotFragment hotFragment = HotFragment_.builder()
                .mNotifications(mArraylistsHeader)
                .build();
        replaceFragment(hotFragment, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setNotificationHot();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.home));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setDefaultData() {

        for (int i = 0; i < 10; i++) {
            Notification notification = new Notification();
            if (i % 2 == 0) {
                notification.setIsFavorite(true);
            } else {
                notification.setIsFavorite(false);
            }
            notification.setIsHot(true);
            notification.setIsRead(false);
            notification.setMAvata(R.drawable.p2);
            notification.setMContent("Đã có lúc anh mong tim mình bé lại. Để nỗi nhớ em không thể " +
                    "nào thêm nữa. Đã có lúc anh mong ngừng thời gian trôi. Để những dấu yêu sẽ không phai mờ");
            notification.setMSender("Le Thai Son");
            notification.setMTittle("Thong bao hop khan cap");
            notification.setMTime("14:32 PM, 06/10");
            mArraylistsHeader.add(notification);
        }

        for (int i = 0; i < 40; i++) {
            Notification notification = new Notification();
            notification.setIsFavorite(true);
            notification.setIsHot(false);
            notification.setIsRead(false);
            notification.setMAvata(R.drawable.p1);
            notification.setMContent("Đã có lúc anh mong tim mình bé lại. Để nỗi nhớ em không thể " +
                    "nào thêm nữa. Đã có lúc anh mong ngừng thời gian trôi. Để những dấu yêu sẽ không phai mờ");
            notification.setMSender("Le Thai Son");
            notification.setMTittle("Thong bao hop khan cap");
            notification.setMTime("14:32 PM, 06/10");
            mArraylists.add(notification);
        }
    }

    @Override
    public void OnCallDetails(ArrayList<Notification> arrayList, int position) {
        mArraylists.get(position).setIsRead(true);
        NotificationDetailFragment notificationDetailFragment = NotificationDetailFragment_.builder()
                .mNotifications(arrayList)
                .mPosition(position)
                .build();
        replaceFragment(notificationDetailFragment, false);
    }
}