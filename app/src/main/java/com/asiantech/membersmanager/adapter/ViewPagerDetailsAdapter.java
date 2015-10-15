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

import java.util.ArrayList;

/**
 *
 * Created by xuanphu on 09/10/2015.
 */
public class ViewPagerDetailsAdapter extends PagerAdapter {
    private ArrayList<Notification> mArraylists;
    private Context mContext;

    public ViewPagerDetailsAdapter(ArrayList<Notification> mArraylists, Context mContext) {
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
        com.asiantech.membersmanager.views.CircleImageView imgAvataDetail;
        TextView tvSenderDetail;
        TextView tvTimeDetail;
        TextView tvContentDetail;
        TextView tvTitleDetail;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_fragment_details, container, false);
        imgAvataDetail = (com.asiantech.membersmanager.views.CircleImageView)itemView.findViewById(R.id.imgAvataDetail);
        tvSenderDetail = (TextView)itemView.findViewById(R.id.tvSenderDetail);
        tvTimeDetail = (TextView)itemView.findViewById(R.id.tvTimeDetail);
        tvContentDetail = (TextView)itemView.findViewById(R.id.tvContentDetail);
        tvTitleDetail = (TextView)itemView.findViewById(R.id.tvTittleDetail);

        imgAvataDetail.setImageResource(mArraylists.get(position).getMAvata());
        tvSenderDetail.setText(mArraylists.get(position).getMSender());
        tvTimeDetail.setText(mArraylists.get(position).getMTime());
        tvContentDetail.setText(mArraylists.get(position).getMContent());
        tvTitleDetail.setText(mArraylists.get(position).getMTittle());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
