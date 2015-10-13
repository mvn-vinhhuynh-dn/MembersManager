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
import com.asiantech.membersmanager.interfaces.CallFavorite;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.views.CircleImageView;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanphu on 08/10/2015.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Notification> mArraylists;
    private CallDetail callDetail;
    private CallFavorite callFavorite;
    private List<Integer> mIntegers = new ArrayList<>();
    private RemoveFavorite mRemoveFavorite;

    public FavoriteAdapter(Context mContext, ArrayList<Notification> mArraylists, CallDetail callDetail, CallFavorite callFavorite, RemoveFavorite removeFavorite) {
        this.mContext = mContext;
        this.mArraylists = mArraylists;
        this.callDetail = callDetail;
        this.callFavorite = callFavorite;
        this.mRemoveFavorite = removeFavorite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_favorite, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (!mArraylists.get(position).getIsChecked()) {
            holder.imgAvataOne.setImageResource(mArraylists.get(position).getMAvata());
            holder.swipeLayout.setBackgroundColor(mContext
                    .getResources().getColor(R.color.white));
        } else {
            holder.imgAvataOne.setImageResource(R.drawable.ic_checked);
            holder.swipeLayout.setBackgroundDrawable(mContext
                    .getResources().getDrawable(R.drawable.shadow_view));
        }
        holder.tvSender.setText(mArraylists.get(position).getMSender());
        holder.tvTittle.setText(mArraylists.get(position).getMTittle());
        holder.tvContent.setText(mArraylists.get(position).getMContent());
        holder.tvTime.setText(mArraylists.get(position).getMTime());
        holder.imgAvataOne.setTag(position);
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
                    callFavorite.OnClickFavorite(position, false);
                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mArraylists.get(position).setIsFavorite(true);
                    callFavorite.OnClickFavorite(position, true);
                }
            }
        });
        holder.imgAvataOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myPos = (Integer) v.getTag();
                if (mArraylists.get(myPos).getIsChecked()) {
                    mArraylists.get(myPos).setIsChecked(false);
                    removeItemOnList(myPos, mIntegers);
                    mRemoveFavorite.removeFavorite(mIntegers);

                } else {
                    mArraylists.get(myPos).setIsChecked(true);
                    mIntegers.add(myPos);
                    mRemoveFavorite.removeFavorite(mIntegers);
                }
            }
        });
    }

    private void removeItemOnList(int num, List<Integer> integers) {
        if (integers.size() > 0) {
            for (int i = 0; i < integers.size(); i++) {
                if (num == integers.get(i)) {
                    integers.remove(i);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mArraylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgAvataOne;
        ImageView imgHot;
        ImageView imgFavorite;
        ImageView imgDelete;
        TextView tvSender;
        TextView tvTittle;
        TextView tvContent;
        TextView tvTime;
        RelativeLayout rlTittle;
        SwipeLayout swipeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvataOne = (CircleImageView) itemView.findViewById(R.id.imgAvata_favorite_one);
            tvSender = (TextView) itemView.findViewById(R.id.tvSender_favorite);
            tvTittle = (TextView) itemView.findViewById(R.id.tvTittle_favorite);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent_favorite);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime_favorite);
            imgHot = (ImageView) itemView.findViewById(R.id.imgHot_favorite);
            imgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite_favorite);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete_favorite);
            rlTittle = (RelativeLayout) itemView.findViewById(R.id.rlTop_favorite);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe_favorite);
        }
    }

    public interface RemoveFavorite {
        void removeFavorite(List<Integer> integers);
    }
}
