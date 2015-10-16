package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.views.CircleImageView;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/8/15.
 */
public class TimeSheetAdapter extends RecyclerView
        .Adapter<TimeSheetAdapter.ViewHolder> {
    private ArrayList<Notification> mDatas = new ArrayList<>();
    private Context mContext;
    private CallDetailItem mCallDetail;

    public TimeSheetAdapter(ArrayList<Notification> mDatas, Context mContext, CallDetailItem mCallDetail) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mCallDetail = mCallDetail;
    }

    @Override
    public TimeSheetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TimeSheetAdapter.ViewHolder holder, final int position) {
        holder.imgAvata.setImageResource(mDatas.get(position).getMAvata());
        holder.tvSender.setText(mDatas.get(position).getMSender());
        holder.tvTittle.setText(mDatas.get(position).getMTittle());
        holder.tvContent.setText(mDatas.get(position).getMContent());
        holder.tvTime.setText(mDatas.get(position).getMTime());

        if (mDatas.get(position).getIsHot()) {
            holder.imgHot.setVisibility(View.VISIBLE);
        } else {
            holder.imgHot.setVisibility(View.INVISIBLE);
        }

        checkFavorite(holder, position);
        checkRead(holder, position);
        holder.rlTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCallDetail.OnCallDetails(mDatas, position);
            }
        });
    }

    private void checkRead(final ViewHolder holder, final int position) {
        if (mDatas.get(position).getIsRead()) {
            holder.tvSender.setTypeface(null, Typeface.NORMAL);
            holder.tvTime.setTypeface(null, Typeface.NORMAL);
            holder.tvTittle.setTypeface(null, Typeface.NORMAL);
            holder.rlParent.setBackgroundColor(mContext
                    .getResources().getColor(R.color.white));
        } else {
            holder.tvSender.setTypeface(null, Typeface.BOLD);
            holder.tvTime.setTypeface(null, Typeface.BOLD);
            holder.tvTittle.setTypeface(null, Typeface.BOLD);
            holder.rlParent.setBackgroundDrawable(mContext
                    .getResources().getDrawable(R.drawable.shadow_view));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgAvata;
        ImageView imgHot;
        ImageView imgFavorite;
        TextView tvSender;
        TextView tvTittle;
        TextView tvContent;
        TextView tvTime;
        RelativeLayout rlTittle;
        RelativeLayout rlParent;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (CircleImageView) itemView.findViewById(R.id.imgAvata);
            tvSender = (TextView) itemView.findViewById(R.id.tvSender);
            tvTittle = (TextView) itemView.findViewById(R.id.tvTittle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            imgHot = (ImageView) itemView.findViewById(R.id.imgHot);
            imgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
            rlTittle = (RelativeLayout) itemView.findViewById(R.id.rlTop);
            rlParent = (RelativeLayout) itemView.findViewById(R.id.rlparent);
        }
    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mDatas.get(position).getIsFavorite()) {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).getIsFavorite()) {
                    holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
                    mDatas.get(position).setIsFavorite(false);
                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mDatas.get(position).setIsFavorite(true);
                }
            }
        });
    }
}