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
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.interfaces.CallFavorite;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.daimajia.swipe.util.Attributes;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment implements CallDetailItem, CallFavorite, RemoveFavorite {
    @ViewById(R.id.recyclerFavorite)
    RecyclerView recyclerFavorite;
    @ViewById(R.id.swipeRefreshLayoutFavorite)
    SwipeRefreshLayout mSwipeRefreshLayoutFavorite;

    private ArrayList<Notification> mArraylists;
    private ArrayList<Notification> mArraylistsTam;
    private FavoriteAdapter mAdapter;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private List<Integer> mIntegers;

    public FavoriteFragment() {
        mIntegers = new ArrayList<>();
        mArraylistsTam = new ArrayList<>();
        mArraylists = new ArrayList<>();
    }

    @AfterViews
    void afterView() {
        setAdapter();
        addListener();
    }

    private void setAdapter() {
        mAdapter = new FavoriteAdapter(getActivity(), mArraylistsTam, this, this, this);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerFavorite.addItemDecoration(new DividerItemDecoration(getResources()
                .getDrawable(R.drawable.divider)));

        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mScaleInAnimationAdapter.setDuration(500);
        mScaleInAnimationAdapter.setFirstOnly(false);
        recyclerFavorite.setAdapter(mScaleInAnimationAdapter);
        mAdapter.setMode(Attributes.Mode.Multiple);
    }

    private void addListener() {
        mSwipeRefreshLayoutFavorite.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayoutFavorite.setRefreshing(false);
                    }

                }, 2000);
            }
        });
    }

    private void fakedata() {
        mArraylists.clear();
        for (int i = 0; i < 20; i++) {
            Notification notification3 = new Notification();
            notification3.setIsFavorite(true);
            notification3.setIsHot(false);
            notification3.setMAvata(R.drawable.p3);
            notification3.setIsChecked(false);
            notification3.setMContent("Đã có lúc anh mong tim mình bé lại " +
                    "Để nỗi nhớ em không thể nào thêm nữa" +
                    "Đã có lúc anh mong ngừng thời gian trôi " +
                    "Để những dấu yêu sẽ không phai mờ" +
                    "" +
                    "Nếu không hát lên nặng lòng da diết" +
                    "Nếu không nói ra làm sao biết" +
                    "Anh thương em" +
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
            notification3.setMTittle("Thong bao hop khan cap---" + i);
            notification3.setMTime("14:32 PM, 06/10");
            mArraylists.add(notification3);
        }
        mArraylistsTam.clear();
        for (int i = 0; i < mArraylists.size(); i++) {
            if (mArraylists.get(i).getIsFavorite()) {
                mArraylistsTam.add(mArraylists.get(i));
            }
        }
        mScaleInAnimationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIntegers.clear();
        mAdapter.clearSizeDelete();
        mAdapter.setIsClick(false);
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
        fakedata();
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
        mArraylistsTam.get(position).setIsFavorite(isFavorite);
    }

    public void onDelete() {
        Collections.sort(mIntegers);
        if (mIntegers.size() < mArraylistsTam.size()) {
            for (int i = 0; i < mIntegers.size(); i++) {
                int num = mIntegers.get(i);
                mArraylistsTam.remove(num - i);
                mScaleInAnimationAdapter.notifyItemRemoved(mIntegers.get(i));
                mScaleInAnimationAdapter.notifyItemRangeChanged(mIntegers.get(i), mArraylistsTam.size());
            }
            mAdapter.clearSizeDelete();
            //Update header
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
        mAdapter.setIsClick(false);
        mScaleInAnimationAdapter.setFirstOnly(false);
        mScaleInAnimationAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeFavorite(List<Integer> integers) {
        //Update header
        if (integers.size() > 0) {
            mScaleInAnimationAdapter.setFirstOnly(true);
            mScaleInAnimationAdapter.notifyDataSetChanged();
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite_delete));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_DELETE);
            mOnBaseFragmentListener.updateTvRight(integers.size());
            mScaleInAnimationAdapter.notifyDataSetChanged();
        } else {
            mScaleInAnimationAdapter.setFirstOnly(true);
            mScaleInAnimationAdapter.notifyDataSetChanged();
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.title_favorite));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
            mScaleInAnimationAdapter.notifyDataSetChanged();
        }
        //Do delete
        mIntegers.clear();
        mIntegers.addAll(integers);
    }

    @Override
    public void removeSingleItems(int pos, SwipeLayout swipeLayout) {
        SwipeItemRecyclerMangerImpl mSwipeItemRecyclerMangerImpl = mAdapter.getSwipeItemRecyclerMangerImpl();
        mSwipeItemRecyclerMangerImpl.removeShownLayouts(swipeLayout);
        mArraylistsTam.remove(pos);
        mScaleInAnimationAdapter.notifyItemRemoved(pos);
        mScaleInAnimationAdapter.notifyItemRangeChanged(pos, mArraylistsTam.size());
        mSwipeItemRecyclerMangerImpl.closeAllItems();
    }
}