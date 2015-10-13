package com.asiantech.membersmanager.fragment;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.FavoriteAdapter;
import com.asiantech.membersmanager.adapter.FavoriteAdapter.RemoveFavorite;
import com.asiantech.membersmanager.interfaces.CallDetail;
import com.asiantech.membersmanager.interfaces.CallFavorite;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment implements CallDetail, CallFavorite, RemoveFavorite {
    @ViewById(R.id.recyclerFavorite)
    RecyclerView recyclerFavorite;
    @ViewById(R.id.swipeRefreshLayoutFavorite)
    SwipeRefreshLayout swipeRefreshLayoutFavorite;

    private ArrayList<Notification> mArraylists;
    private ArrayList<Notification> mArraylistsTam;
    private FavoriteAdapter mAdapter;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;

    public FavoriteFragment() {
        mArraylistsTam = new ArrayList<>();
        mArraylists = new ArrayList<>();
        fakedata();
    }

    @AfterViews
    void afterView() {
        for (int i = 0; i < mArraylists.size(); i++) {
            if (mArraylists.get(i).getIsFavorite()) {
                mArraylistsTam.add(mArraylists.get(i));
            }
        }
        mAdapter = new FavoriteAdapter(getActivity(), mArraylistsTam, this, this, this);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerFavorite.addItemDecoration(new DividerItemDecoration(getResources()
                .getDrawable(R.drawable.divider)));

        // Add animation
//        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
//        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
//        mScaleInAnimationAdapter.setDuration(500);
//        mScaleInAnimationAdapter.setFirstOnly(false);

        recyclerFavorite.setAdapter(mAdapter);

        swipeRefreshLayoutFavorite.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        swipeRefreshLayoutFavorite.setRefreshing(false);
                    }

                }, 2000);
            }
        });
    }

    private void fakedata() {
        for (int i = 0; i < 20; i++) {
            Notification notification3 = new Notification();
            notification3.setIsFavorite(true);
            notification3.setIsHot(false);
            notification3.setMAvata(R.drawable.p3);
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
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }

    @Override
    public void OnCallDetails(ArrayList<Notification> arrayList, int position) {
        NotificationDetailFragment notificationDetailFragment = NotificationDetailFragment_.builder()
                .mNotifications(arrayList)
                .mPosition(position)
                .build();
        replaceFragment(notificationDetailFragment, false);
    }

    @Override
    public void OnClickFavorite(int position, boolean isFavorite) {
        mArraylists.get(position).setIsFavorite(isFavorite);
    }

    public void onDelete() {
        //TODO delete function
    }

    @Override
    public void removeFavorite(List<Integer> integers) {
        if (integers.size() > 0) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite_delete));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DELETE);
            mOnBaseFragmentListener.updateTvRight(integers.size());
            mAdapter.notifyDataSetChanged();
        } else {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
            mAdapter.notifyDataSetChanged();
        }
    }
}
