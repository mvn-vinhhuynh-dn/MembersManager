package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.interfaces.CallDetail;
import com.asiantech.membersmanager.views.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xuanphu on 06/10/2015.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Notification> mNotifications;
    private CallDetail callDetail;

    public HomeAdapter(Context mContext, ArrayList<Notification> notifications, CallDetail callDetail) {
        this.mContext = mContext;
        this.mNotifications = notifications;
        this.callDetail = callDetail;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imgAvata.setImageResource(mNotifications.get(position).getMAvata());
        holder.tvSender.setText(mNotifications.get(position).getMSender());
        holder.tvTittle.setText(mNotifications.get(position).getMTittle());
        holder.tvContent.setText(mNotifications.get(position).getMContent());
        holder.tvTime.setText(mNotifications.get(position).getMTime());

        if (mNotifications.get(position).getIsHot()) {
            holder.imgHot.setVisibility(View.VISIBLE);
        } else {
            holder.imgHot.setVisibility(View.INVISIBLE);
        }

        checkFavorite(holder, position);

        holder.rlTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail.OnCallDetails(mNotifications.get(position));
            }
        });

    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mNotifications.get(position).getIsFavorite()) {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNotifications.get(position).getIsFavorite()) {
                    holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
                    mNotifications.get(position).setIsFavorite(false);
                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mNotifications.get(position).setIsFavorite(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgAvata;
        ImageView imgHot;
        ImageView imgDelete;
        ImageView imgFavorite;
        TextView tvSender;
        TextView tvTittle;
        TextView tvContent;
        TextView tvTime;
        RelativeLayout rlTittle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (CircleImageView) itemView.findViewById(R.id.imgAvata);
            tvSender = (TextView) itemView.findViewById(R.id.tvSender);
            tvTittle = (TextView) itemView.findViewById(R.id.tvTittle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            imgHot = (ImageView) itemView.findViewById(R.id.imgHot);
            imgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
            rlTittle = (RelativeLayout) itemView.findViewById(R.id.rlTop);
        }
    }

}