package com.asiantech.membersmanager.adapter;

import android.content.Context;
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
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavigationDrawerItem> mDatas = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;
    private final int NUM_ITEMS = 6;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = data;
    }

    public void delete(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_home));
                holder.title.setText(R.string.home);
                break;
            case 1:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_favorite));
                holder.title.setText(R.string.menu_favorite);
                break;
            case 2:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_time_sheet));
                holder.title.setText(R.string.time_sheet);
                break;
            case 3:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_mail_menu));
                holder.title.setText(R.string.out_work);
                break;
            case 4:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_help_feedback));
                holder.title.setText(R.string.help_feedback);
                break;

            case 5:
                holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_logout));
                holder.title.setText(R.string.logout);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
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
