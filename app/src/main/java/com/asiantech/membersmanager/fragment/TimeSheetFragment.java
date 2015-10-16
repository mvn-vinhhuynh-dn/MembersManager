package com.asiantech.membersmanager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.asiantech.membersmanager.MainActivity;
import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.TimeSheetAdapter;
import com.asiantech.membersmanager.interfaces.CallDetailItem;
import com.asiantech.membersmanager.models.Notification;
import com.asiantech.membersmanager.utils.DividerItemDecoration;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.marcohc.robotocalendar.RobotoCalendarView;
import com.marcohc.robotocalendar.RobotoCalendarView.RobotoCalendarListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_timesheet)
public class TimeSheetFragment extends BaseFragment implements RobotoCalendarListener, CallDetailItem {

    private ArrayList<Notification> mListNotifications = new ArrayList<>();
    private TimeSheetAdapter mAdapter;

    @ViewById(R.id.recycler_timeSheet)
    RecyclerView mRecycleTimeSheet;
    private RecyclerViewHeader mRecyclerViewHeader;
    private RobotoCalendarView mRobotoCalendarView;
    private Calendar mCurrentCalendar;
    private int mCurrentMonthIndex;
    private int mDay;
    private int mMoth;
    private int mYear;

    @AfterViews
    public void afterView() {
        initView();
        initData();
        setAdapter();
        fakeData();
    }

    private void initData() {
        mRecycleTimeSheet.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
        mRecyclerViewHeader.attachTo(mRecycleTimeSheet);
        mRobotoCalendarView.setRobotoCalendarListener(this);
        mCurrentMonthIndex = 0;
        mCurrentCalendar = Calendar.getInstance(Locale.getDefault());

        // Mark current day
        mRobotoCalendarView.markDayAsCurrentDay(mCurrentCalendar.getTime());
    }

    private void initView() {
        mRecyclerViewHeader = RecyclerViewHeader
                .fromXml(getActivity(), R.layout.header_recyclerview);
        mRobotoCalendarView = (RobotoCalendarView) mRecyclerViewHeader
                .findViewById(R.id.robotoCalendarPicker);
    }

    private void setAdapter() {
        mAdapter = new TimeSheetAdapter(mListNotifications,getActivity(), this);
        mRecycleTimeSheet.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(500);
        scaleAdapter.setFirstOnly(false);
        mRecycleTimeSheet.setAdapter(scaleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.time_sheet));
            mOnBaseFragmentListener.setTypeHeader(MainActivity.TYPE_HOME);
        }
    }

    private void fakeData() {
        for (int i = 0; i < 10; i++) {
            Notification notification = new Notification();
            notification.setIsFavorite(true);
            notification.setIsHot(false);
            notification.setIsRead(false);
            notification.setMAvata(R.drawable.p1);
            notification.setMContent("Đã có lúc anh mong tim mình bé lại. Để nỗi nhớ em không thể " +
                    "nào thêm nữa. Đã có lúc anh mong ngừng thời gian trôi. Để những dấu yêu sẽ không phai mờ");
            notification.setMSender("Le Thai Son");
            notification.setMTittle("Thong bao hop khan cap");
            notification.setMTime("14:32 PM, 06/10");
            mListNotifications.add(notification);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDateSelected(Date date) {
        //getDate selected
        getDayMonthYear(date);
        // Mark calendar day
        mRobotoCalendarView.markDayAsSelectedDay(date);
        // Mark that day with random colors
        final Random random = new Random(System.currentTimeMillis());
        final int style = random.nextInt(3);
        switch (style) {
            case 0:
                mRobotoCalendarView
                        .markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR, date);
                break;
            case 1:
                mRobotoCalendarView
                        .markSecondUnderlineWithStyle(RobotoCalendarView.GREEN_COLOR, date);
                break;
            case 2:
                mRobotoCalendarView
                        .markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, date);
                break;
            default:
                break;
        }
    }

    private void getDayMonthYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        mYear = cal.get(Calendar.YEAR);
        mMoth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        Toast.makeText(getActivity(), " " + mDay + "/"
                + (mMoth + 1) + "/"
                + mYear, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onRightButtonClick() {
        mCurrentMonthIndex++;
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        mCurrentMonthIndex--;
        updateCalendar();
    }

    private void updateCalendar() {
        mCurrentCalendar = Calendar.getInstance(Locale.getDefault());
        mCurrentCalendar.add(Calendar.MONTH, mCurrentMonthIndex);
        mRobotoCalendarView.initializeCalendar(mCurrentCalendar);
    }

    @Override
    public void OnCallDetails(ArrayList<Notification> arrayList, int position) {
        arrayList.get(position).setIsRead(true);
        NotificationDetailFragment notificationDetailFragment = NotificationDetailFragment_.builder()
                .mNotifications(arrayList)
                .mPosition(position)
                .build();
        replaceFragment(notificationDetailFragment, false);
    }
}
