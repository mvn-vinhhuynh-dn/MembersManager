package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.models.Notification;

import java.util.ArrayList;

/**
 *
 * Created by xuanphu on 06/10/2015.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Notification> mArraylist;

    public HomeAdapter(Context mContext, ArrayList<Notification> mArraylist) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgAvata.setImageResource(mArraylist.get(position).getMAvata());
        holder.tvSender.setText(mArraylist.get(position).getMSender());
        holder.tvTittle.setText(mArraylist.get(position).getMTittle());
        holder.tvContent.setText(mArraylist.get(position).getMContent());
        holder.tvTime.setText(mArraylist.get(position).getMTime());
        if (mArraylist.get(position).getIsHot()){

        }else {
            holder.imgHot.setVisibility(View.INVISIBLE);
        }

        checkFavorite(holder, position);

        if (position == mArraylist.size()-1){
            holder.viewLine.setVisibility(View.INVISIBLE);
        }
    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mArraylist.get(position).getIsFavorite()){
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArraylist.get(position).getIsFavorite()){
                    holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
                    mArraylist.get(position).setIsFavorite(false);
                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mArraylist.get(position).setIsFavorite(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        com.asiantech.membersmanager.utils.CircleImageView imgAvata;
        ImageView imgHot, imgFavorite;
        TextView tvSender, tvTittle, tvContent, tvTime;
        View viewLine;
        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (com.asiantech.membersmanager.utils.CircleImageView)itemView.findViewById(R.id.imgAvata);
            tvSender = (TextView)itemView.findViewById(R.id.tvSender);
            tvTittle = (TextView)itemView.findViewById(R.id.tvTittle);
            tvContent = (TextView)itemView.findViewById(R.id.tvContent);
            tvTime = (TextView)itemView.findViewById(R.id.tvTime);
            imgHot = (ImageView)itemView.findViewById(R.id.imgHot);
            imgFavorite = (ImageView)itemView.findViewById(R.id.imgFavorite);
            viewLine = (View)itemView.findViewById(R.id.viewLine);
        }
    }
}
