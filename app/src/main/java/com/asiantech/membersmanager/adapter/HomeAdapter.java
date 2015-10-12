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
import com.asiantech.membersmanager.interfaces.CallDetail;
import com.asiantech.membersmanager.interfaces.StickyHeaderAdapter;
import com.asiantech.membersmanager.models.Notification;

import java.util.ArrayList;

/**
 * Created by xuanphu on 06/10/2015.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements StickyHeaderAdapter<HomeAdapter.HeaderHolder> {
    private Context mContext;
    private ArrayList<Notification> mArraylists;
    private ArrayList<Notification> mArraylistsHeader;
    private CallDetail callDetail;

    public HomeAdapter(Context mContext, ArrayList<Notification> mArraylists, ArrayList<Notification> mArraylistsHeader, CallDetail callDetail) {
        this.mContext = mContext;
        this.mArraylists = mArraylists;
        this.mArraylistsHeader = mArraylistsHeader;
        this.callDetail = callDetail;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_home, parent, false);
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

        if (mArraylists.get(position).getIsHot()) {
            holder.imgHot.setVisibility(View.VISIBLE);
        } else {
            holder.imgHot.setVisibility(View.INVISIBLE);
        }

        checkFavorite(holder, position);

        holder.rlTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail.OnCallDetails(mArraylists, position);
            }
        });

    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mArraylists.get(position).getIsFavorite()) {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArraylists.get(position).getIsFavorite()) {
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        com.asiantech.membersmanager.views.CircleImageView imgAvata;
        ImageView imgHot, imgFavorite, imgDelete;
        TextView tvSender, tvTittle, tvContent, tvTime;
        RelativeLayout rlTittle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (com.asiantech.membersmanager.views.CircleImageView) itemView.findViewById(R.id.imgAvata);
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

    static class HeaderHolder extends RecyclerView.ViewHolder {
        com.asiantech.membersmanager.views.CircleImageView imgAvataHeader;
        ImageView imgHotHeader, imgFavoriteHeader, imgDeleteHeader;
        TextView tvSenderHeader, tvTittleHeader, tvContentHeader, tvTimeHeader;
        RelativeLayout rlTittleHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            imgAvataHeader = (com.asiantech.membersmanager.views.CircleImageView) itemView.findViewById(R.id.imgAvataHeader);
            tvSenderHeader = (TextView) itemView.findViewById(R.id.tvSenderHeader);
            tvTittleHeader = (TextView) itemView.findViewById(R.id.tvTittleHeader);
            tvContentHeader = (TextView) itemView.findViewById(R.id.tvContentHeader);
            tvTimeHeader = (TextView) itemView.findViewById(R.id.tvTimeHeader);
            imgHotHeader = (ImageView) itemView.findViewById(R.id.imgHotHeader);
            imgFavoriteHeader = (ImageView) itemView.findViewById(R.id.imgFavoriteHeader);
            imgDeleteHeader = (ImageView) itemView.findViewById(R.id.imgDeleteHeader);
            rlTittleHeader = (RelativeLayout) itemView.findViewById(R.id.rlTopHeader);
        }
    }

    // header
    @Override
    public long getHeaderId(int position) {
        return 1;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_fragment_home, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        if (position < mArraylistsHeader.size()) {
            viewholder.imgAvataHeader.setImageResource(mArraylistsHeader.get(position).getMAvata());
            viewholder.tvSenderHeader.setText(mArraylistsHeader.get(position).getMSender());
            viewholder.tvTittleHeader.setText(mArraylistsHeader.get(position).getMTittle());
            viewholder.tvContentHeader.setText(mArraylistsHeader.get(position).getMContent());
            viewholder.tvTimeHeader.setText(mArraylistsHeader.get(position).getMTime());
        }
    }
}