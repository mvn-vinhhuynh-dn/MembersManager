package com.asiantech.membersmanager.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.HomeAdapter;
import com.asiantech.membersmanager.interfaces.CallDetail;
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
public class HomeFragment extends BaseFragment implements CallDetail {
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
        setNotificationHot();
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
        imgAvataHeader.setImageResource(mArraylistsHeader.get(0).getMAvata());
        tvSenderHeader.setText(mArraylistsHeader.get(0).getMSender());
        tvTittleHeader.setText(mArraylistsHeader.get(0).getMTittle());
        tvTimeHeader.setText(mArraylistsHeader.get(0).getMTime());
        tvContentHeader.setText(mArraylistsHeader.get(0).getMContent());

    }

    @Click(R.id.swipeHeader)
    void clickItem() {
        DetailHotNotificationFragment detailHotNotificationFragment = DetailHotNotificationFragment_.builder()
                .mNotifications(mArraylistsHeader)
                .mPosition(0)
                .build();
        replaceFragment(detailHotNotificationFragment, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.home));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setDefaultData() {
        Notification notification1 = new Notification();
        notification1.setIsFavorite(true);
        notification1.setIsHot(true);
        notification1.setIsRead(false);
        notification1.setMAvata(R.drawable.p1);
        notification1.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification1.setMSender("Le Thai Son");
        notification1.setMTittle("Thong bao hop khan cap");
        notification1.setMTime("14:32 PM, 06/10");
        mArraylistsHeader.add(notification1);

        mArraylistsHeader.add(notification1);

        Notification notification2 = new Notification();
        notification2.setIsFavorite(false);
        notification2.setIsHot(false);
        notification2.setIsRead(false);
        notification2.setMAvata(R.drawable.p2);
        notification2.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification2.setMSender("Le Thai Son");
        notification2.setMTittle("Thong bao hop khan cap");
        notification2.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification2);
        mArraylistsHeader.add(notification2);

        Notification notification3 = new Notification();
        notification3.setIsFavorite(true);
        notification3.setIsHot(false);
        notification3.setIsRead(false);
        notification3.setMAvata(R.drawable.p4);
        notification3.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\n" +
                "Để những dấu yêu sẽ không phai mờ\n" +
                "\n" +
                "Nếu không hát lên nặng lòng da diết\n" +
                "Nếu không nói ra làm sao biết\n" +
                "Anh thương em\n" +
                "Anh sẽ nói em nghe những điều chưa bao giờ\n" +
                "\n" +
                "Bình minh khuất lấp sau màn đêm như nỗi lòng anh\n" +
                "Chất chứa lâu nay em đâu nào hay biết\n" +
                "Hoàng hôn tắt nắng hay vì anh không hiểu được em\n" +
                "Dập tan bao yêu dấu lụi tàn\n" +
                "\n" +
                "Cất tiếng hát nghe sao lòng nhẹ cơn sầu\n" +
                "Dẫu có chút vương, chút ân tình chôn giấu\n" +
                "Đếm những nhớ thương thầm lặng trên tay\n" +
                "Nghe sao buốt thêm, ướt đôi vai gầy.\n" +
                "\n" +
                "Nếu không hát lên nặng lòng da diết\n" +
                "Nếu không nói ra làm sao biết\n" +
                "Anh thương em\n" +
                "Anh sẽ nói em nghe những điều chưa bao giờ\n" +
                "\n" +
                "Bình minh khuất lấp sau màn đêm như nỗi lòng anh\n" +
                "Chất chứa lâu nay em đâu nào hay biết\n" +
                "Hoàng hôn tắt nắng hay vì anh không hiểu được em\n" +
                "Dập tan bao yêu dấu lụi tàn\n" +
                "\n" +
                "Vì anh câm nín chôn sâu yêu thương anh trao đến em,\n" +
                "Lặng nhìn em lướt qua bên đời.\n" +
                "Một mai ai biết cơn mê đưa em vào vòng tay mới.\n" +
                "Anh sẽ chờ phía sau giấc mơ của em\n" +
                "Anh sẽ chờ để nói những điều chưa bao giờ");
        notification3.setMSender("Le Thai Son");
        notification3.setMTittle("Thong bao hop khan cap");
        notification3.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification3);

        Notification notification4 = new Notification();
        notification4.setIsFavorite(false);
        notification4.setIsHot(false);
        notification4.setIsRead(true);
        notification4.setMAvata(R.drawable.p4);
        notification4.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification4.setMSender("Le Thai Son");
        notification4.setMTittle("Thong bao hop khan cap");
        notification4.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification4);

        Notification notification5 = new Notification();
        notification5.setIsFavorite(true);
        notification5.setIsHot(false);
        notification5.setIsRead(false);
        notification5.setMAvata(R.drawable.p2);
        notification5.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification5.setMSender("Le Thai Son");
        notification5.setMTittle("Thong bao hop khan cap");
        notification5.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification5);

        Notification notification6 = new Notification();
        notification6.setIsFavorite(true);
        notification6.setIsHot(false);
        notification6.setIsRead(false);
        notification6.setMAvata(R.drawable.p3);
        notification6.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification6.setMSender("Le Thai Son");
        notification6.setMTittle("Thong bao hop khan cap");
        notification6.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification6);

        Notification notification7 = new Notification();
        notification7.setIsFavorite(true);
        notification7.setIsHot(false);
        notification7.setIsRead(false);
        notification7.setMAvata(R.drawable.p1);
        notification7.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification7.setMSender("Le Thai Son");
        notification7.setMTittle("Thong bao hop khan cap");
        notification7.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification7);

        Notification notification8 = new Notification();
        notification8.setIsFavorite(true);
        notification8.setIsHot(false);
        notification8.setIsRead(false);
        notification8.setMAvata(R.drawable.p1);
        notification8.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                "Để những dấu yêu sẽ không phai mờ\\n\"");
        notification8.setMSender("Le Thai Son");
        notification8.setMTittle("Thong bao hop khan cap");
        notification8.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification8);

        for (int i = 0; i < 40; i++) {
            Notification notification = new Notification();
            notification.setIsFavorite(true);
            notification.setIsHot(false);
            notification.setIsRead(false);
            notification.setMAvata(R.drawable.p1);
            notification.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                    "Để nỗi nhớ em không thể nào thêm nữa\\n\" +\n" +
                    "Đã có lúc anh mong ngừng thời gian trôi\\n\" +\n" +
                    "Để những dấu yêu sẽ không phai mờ\\n\"");
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