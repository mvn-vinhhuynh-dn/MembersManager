package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.models.NavigationDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 12-03-2015.
 */
public class NavigationDrawerAdapter extends RecyclerView
        .Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavigationDrawerItem> mDatas = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;
    private int mPosSelected = 0;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = data;
    }

    public void setmPosSelected(int mPosSelected) {
        this.mPosSelected = mPosSelected;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mPosSelected == position) {
            holder.imageView.setSelected(true);
            holder.title.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.imageView.setSelected(false);
            holder.title.setTextColor(mContext.getResources().getColor(R.color.textview_textcolor_616161));

        }
        switch (position) {
            case 0:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.selector_home));
                holder.title.setText(R.string.home);
                break;
            case 1:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.selector_favorite));
                holder.title.setText(R.string.menu_favorite);
                break;
            case 2:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.selector_time_sheet));
                holder.title.setText(R.string.time_sheet);
                break;
            case 3:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.selector_vacationday));
                holder.title.setText(R.string.out_work);
                break;
            case 4:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.selector_help));
                holder.title.setText(R.string.help_feedback);
                break;
            case 5:
                holder.imageView.setBackground(ContextCompat
                        .getDrawable(mContext, R.drawable.ic_logout_normal));
                holder.title.setText(R.string.logout);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.img_icon);
        }
    }
}