package com.asiantech.membersmanager.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.HomeAdapter;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.RecyclerItemClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/5/15.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewById(R.id.recyclerHome)
    RecyclerView mRecycleHome;

    private ArrayList<Notification> mArraylists;

    public HomeFragment() {
        mArraylists = new ArrayList<>();
        fakedata();
    }

    @AfterViews
    void affterView() {
        HomeAdapter adapter = new HomeAdapter(getActivity(), mArraylists);
        mRecycleHome.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecycleHome.setAdapter(adapter);
        mRecycleHome.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleHome, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                replaceFragment(new NotificationDetailFragment_(), getString(R.string.title_detail), false);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mOnBaseFragmentListener != null) mOnBaseFragmentListener.setTitleHeader(getString(R.string.home));
    }

    private void fakedata() {
        Notification notification1 = new Notification();
        notification1.setIsFavorite(true);
        notification1.setIsHot(true);
        notification1.setMAvata(R.drawable.p1);
        notification1.setMContent("Thong basdsd fsdsdf");
        notification1.setMSender("Le Thai Son");
        notification1.setMTittle("Thong bao hop khan cap");
        notification1.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification1);

        Notification notification2 = new Notification();
        notification2.setIsFavorite(false);
        notification2.setIsHot(false);
        notification2.setMAvata(R.drawable.p2);
        notification2.setMContent("Thong basdsd fsdsdf");
        notification2.setMSender("Le Thai Son");
        notification2.setMTittle("Thong bao hop khan cap");
        notification2.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification2);

        Notification notification3 = new Notification();
        notification3.setIsFavorite(true);
        notification3.setIsHot(false);
        notification3.setMAvata(R.drawable.p3);
        notification3.setMContent("Don xin nghi phep ngay. sdf sf  sdfsd f sdfsdfsdfsdf s dfs df sdfsdfsdf  sdf sdf sd fs df sdf s df sd gdfgdfg dfgdfg d df sdf s df   sd fsdfsdfsdfsdf  sdfsdf");
        notification3.setMSender("Le Thai Son");
        notification3.setMTittle("Thong bao hop khan cap");
        notification3.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification3);

        Notification notification4 = new Notification();
        notification4.setIsFavorite(false);
        notification4.setIsHot(false);
        notification4.setMAvata(R.drawable.p4);
        notification4.setMContent("Thong basdsd fsdsdfffgdfgdfgdf ");
        notification4.setMSender("Le Thai Son");
        notification4.setMTittle("Thong bao hop khan cap");
        notification4.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification4);

        Notification notification5 = new Notification();
        notification5.setIsFavorite(true);
        notification5.setIsHot(false);
        notification5.setMAvata(R.drawable.p2);
        notification5.setMContent("Thong basdsd ");
        notification5.setMSender("Le Thai Son");
        notification5.setMTittle("Thong bao hop khan cap");
        notification5.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification5);

        Notification notification6 = new Notification();
        notification6.setIsFavorite(true);
        notification6.setIsHot(false);
        notification6.setMAvata(R.drawable.p3);
        notification6.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification6.setMSender("Le Thai Son");
        notification6.setMTittle("Thong bao hop khan cap");
        notification6.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification6);

        Notification notification7 = new Notification();
        notification7.setIsFavorite(true);
        notification7.setIsHot(false);
        notification7.setMAvata(R.drawable.p1);
        notification7.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification7.setMSender("Le Thai Son");
        notification7.setMTittle("Thong bao hop khan cap");
        notification7.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification7);

        Notification notification8 = new Notification();
        notification8.setIsFavorite(true);
        notification8.setIsHot(false);
        notification8.setMAvata(R.drawable.p1);
        notification8.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification8.setMSender("Le Thai Son");
        notification8.setMTittle("Thong bao hop khan cap");
        notification8.setMTime("14:32 PM, 06/10");
        mArraylists.add(notification8);
        mArraylists.add(notification8);
        mArraylists.add(notification8);
    }
}
