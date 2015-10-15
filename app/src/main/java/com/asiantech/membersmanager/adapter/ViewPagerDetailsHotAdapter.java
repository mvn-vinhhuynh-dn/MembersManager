package com.asiantech.membersmanager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.views.CircleImageView;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by xuanphu on 12/10/2015.
 */
public class ViewPagerDetailsHotAdapter extends PagerAdapter {
    private ArrayList<Notification> mArraylists;
    private Context mContext;

    public ViewPagerDetailsHotAdapter(ArrayList<Notification> mArraylists, Context mContext) {
        this.mArraylists = mArraylists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mArraylists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater;
        CircleImageView imgAvataDetailHot;
        TextView tvSenderDetailHot;
        TextView tvTimeDetailHot;
        TextView tvContentDetailHot;
        TextView tvTitleDetailHot;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_fragment_details_hot, container, false);
        imgAvataDetailHot = (CircleImageView)itemView.findViewById(R.id.imgAvataDetailHot);
        tvSenderDetailHot = (TextView)itemView.findViewById(R.id.tvSenderDetailHot);
        tvTimeDetailHot = (TextView)itemView.findViewById(R.id.tvTimeDetailHot);
        tvContentDetailHot = (TextView)itemView.findViewById(R.id.tvContentDetailHot);
        tvTitleDetailHot = (TextView)itemView.findViewById(R.id.tvTittleDetailHot);

        imgAvataDetailHot.setImageResource(mArraylists.get(position).getMAvata());
        tvSenderDetailHot.setText(mArraylists.get(position).getMSender());
        tvTimeDetailHot.setText(mArraylists.get(position).getMTime());
        tvContentDetailHot.setText(mArraylists.get(position).getMContent());
        tvTitleDetailHot.setText(mArraylists.get(position).getMTittle());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
