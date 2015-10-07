package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.models.Reason;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/7/15.
 */
public class ReasonDayOffAdapter extends RecyclerView.Adapter<ReasonDayOffAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Reason> mReasons = new ArrayList<>();
    private Context mContext;
    private OnChooseReason mListener;

    public ReasonDayOffAdapter(Context context, ArrayList<Reason> reasons, OnChooseReason onChooseReason) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mReasons = reasons;
        mListener = onChooseReason;
    }

    @Override
    public ReasonDayOffAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_reson_off_day, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReasonDayOffAdapter.MyViewHolder holder, int position) {
        holder.tvReason.setText(mReasons.get(position).getReason());
        holder.cbReason.setTag(position);
        holder.cbReason.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("vvvv", "add");
                } else {
                    Log.d("vvvv", "remove");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReasons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvReason;
        private CheckBox cbReason;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvReason = (TextView) itemView.findViewById(R.id.tvReson);
            cbReason = (CheckBox) itemView.findViewById(R.id.cbChoose);
        }
    }

    public interface OnChooseReason {
        void onShowReason(String reson, int pos, int idReason);
    }
}
