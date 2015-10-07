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
import com.asiantech.membersmanager.fragment.NotificationDetailFragment_;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.CallDetail;

import java.util.ArrayList;

/**
 *
 * Created by xuanphu on 06/10/2015.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Notification> mArraylists;
    private CallDetail callDetail;

    public HomeAdapter(Context mContext, ArrayList<Notification> mArraylists, CallDetail callDetail) {
        this.mContext = mContext;
        this.mArraylists = mArraylists;
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
        holder.imgAvata.setImageResource(mArraylists.get(position).getMAvata());
        holder.tvSender.setText(mArraylists.get(position).getMSender());
        holder.tvTittle.setText(mArraylists.get(position).getMTittle());
        holder.tvContent.setText(mArraylists.get(position).getMContent());
        holder.tvTime.setText(mArraylists.get(position).getMTime());

        if (mArraylists.get(position).getIsHot()){

        }else {
            holder.imgHot.setVisibility(View.INVISIBLE);
        }

        checkFavorite(holder, position);

        holder.rlTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callDetail.OnCallDetails(mArraylists.get(position));
            }
        });

    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mArraylists.get(position).getIsFavorite()){
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArraylists.get(position).getIsFavorite()){
                    holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
                    mArraylists.get(position).setIsFavorite(false);
                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mArraylists.get(position).setIsFavorite(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArraylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        com.asiantech.membersmanager.utils.CircleImageView imgAvata;
        ImageView imgHot, imgFavorite, imgDelete;
        TextView tvSender, tvTittle, tvContent, tvTime;
        RelativeLayout rlTittle;
        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (com.asiantech.membersmanager.utils.CircleImageView)itemView.findViewById(R.id.imgAvata);
            tvSender = (TextView)itemView.findViewById(R.id.tvSender);
            tvTittle = (TextView)itemView.findViewById(R.id.tvTittle);
            tvContent = (TextView)itemView.findViewById(R.id.tvContent);
            tvTime = (TextView)itemView.findViewById(R.id.tvTime);
            imgHot = (ImageView)itemView.findViewById(R.id.imgHot);
            imgFavorite = (ImageView)itemView.findViewById(R.id.imgFavorite);
            imgDelete = (ImageView)itemView.findViewById(R.id.imgDelete);
            rlTittle = (RelativeLayout)itemView.findViewById(R.id.rlTop);
        }
    }

}