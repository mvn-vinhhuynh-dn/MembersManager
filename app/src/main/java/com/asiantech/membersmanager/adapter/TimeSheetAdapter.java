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
import android.widget.Toast;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.views.CircleImageView;
import com.marcohc.robotocalendar.RobotoCalendarView;
import com.marcohc.robotocalendar.RobotoCalendarView.RobotoCalendarListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/8/15.
 */
public class TimeSheetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private ArrayList<Notification> mDatas = new ArrayList<>();
    private Context mContext;
    private CallDetailItem mCallDetail;
    private Calendar mCurrentCalendar;
    private int mCurrentMonthIndex;
    private int mDay;
    private int mMoth;
    private int mYear;

    public TimeSheetAdapter(ArrayList<Notification> mDatas,
                            Context mContext, CallDetailItem mCallDetail) {
        this.mDatas = mDatas;
        mCurrentMonthIndex = 0;
        this.mContext = mContext;
        this.mCallDetail = mCallDetail;
        mCurrentCalendar = Calendar.getInstance(Locale.getDefault());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            View rootViewItem = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycle_home, parent, false);
            return new VHItem(rootViewItem);
        } else if (viewType == TYPE_HEADER) {
            //inflate your layout and pass it to view holder
            View rootViewHeader = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_recyclerview, parent, false);
            return new VHHeader(rootViewHeader);
        }
        throw new RuntimeException("there is no type that matches " +
                "the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder != null && holder instanceof VHItem) {
            //cast holder to VHItem and set data
            final VHItem itemHolder = (VHItem) holder;
            Notification data = getItem(position);
            itemHolder.imgAvata.setImageResource(data.getMAvata());
            itemHolder.tvSender.setText(data.getMSender());
            itemHolder.tvTittle.setText(data.getMTittle());
            itemHolder.tvContent.setText(data.getMContent());
            itemHolder.tvTime.setText(data.getMTime());

            if (data.getIsHot()) {
                itemHolder.imgHot.setVisibility(View.VISIBLE);
            } else {
                itemHolder.imgHot.setVisibility(View.INVISIBLE);
            }

            checkFavorite(itemHolder, position - 1);
            checkRead(itemHolder, position - 1);
            itemHolder.rlTittle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallDetail.OnCallDetails(mDatas, position - 1);
                }
            });
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
            final VHHeader headerHolder = (VHHeader) holder;
            headerHolder.mRobotoCalendarView.setRobotoCalendarListener(new RobotoCalendarListener() {
                @Override
                public void onDateSelected(Date date) {
                    getDayMonthYear(date);
                    headerHolder.mRobotoCalendarView.markDayAsSelectedDay(date);
                    final Random random = new Random(System.currentTimeMillis());
                    final int style = random.nextInt(1);
                    switch (style) {
                        case 0:
                            headerHolder.mRobotoCalendarView
                                    .markFirstUnderlineWithStyle(RobotoCalendarView
                                            .BLUE_COLOR, date);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onRightButtonClick() {
                    mCurrentMonthIndex++;
                    updateCalendar(headerHolder.mRobotoCalendarView);
                }

                @Override
                public void onLeftButtonClick() {
                    mCurrentMonthIndex--;
                    updateCalendar(headerHolder.mRobotoCalendarView);
                }
            });
            headerHolder.mRobotoCalendarView.markDayAsCurrentDay(mCurrentCalendar.getTime());
        }
    }

    private void checkRead(final VHItem holder, final int position) {
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

    private void updateCalendar(RobotoCalendarView robotoCalendarView) {
        mCurrentCalendar = Calendar.getInstance(Locale.getDefault());
        mCurrentCalendar.add(Calendar.MONTH, mCurrentMonthIndex);
        robotoCalendarView.initializeCalendar(mCurrentCalendar);
    }

    private void getDayMonthYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        mYear = cal.get(Calendar.YEAR);
        mMoth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        Toast.makeText(mContext, " " + mDay + "/"
                + (mMoth + 1) + "/"
                + mYear, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private Notification getItem(int position) {
        return mDatas.get(position - 1);
    }

    class VHItem extends RecyclerView.ViewHolder {
        CircleImageView imgAvata;
        ImageView imgHot;
        ImageView imgFavorite;
        TextView tvSender;
        TextView tvTittle;
        TextView tvContent;
        TextView tvTime;
        RelativeLayout rlTittle;
        RelativeLayout rlParent;

        public VHItem(View itemView) {
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

    class VHHeader extends RecyclerView.ViewHolder {
        RobotoCalendarView mRobotoCalendarView;

        public VHHeader(View itemView) {
            super(itemView);
            mRobotoCalendarView = (RobotoCalendarView) itemView
                    .findViewById(R.id.robotoCalendarPicker);
        }
    }

    private void checkFavorite(final VHItem holder, final int position) {
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