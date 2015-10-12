package com.asiantech.membersmanager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.asiantech.membersmanager.R;
import com.asiantech.membersmanager.abstracts.BaseFragment;
import com.asiantech.membersmanager.adapter.TimeSheetAdapter;
import com.asiantech.membersmanager.models.Notification;
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

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
@EFragment(R.layout.fragment_timesheet)
public class TimeSheetFragment extends BaseFragment implements RobotoCalendarListener {

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
        mAdapter = new TimeSheetAdapter(getActivity(), mListNotifications);
        mRecycleTimeSheet.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null)
            mOnBaseFragmentListener.setTitleHeader(getString(R.string.time_sheet));
    }

    private void fakeData() {
        Notification notification1 = new Notification();
        notification1.setIsFavorite(true);
        notification1.setIsHot(true);
        notification1.setMAvata(R.drawable.p1);
        notification1.setMContent("Thong basdsd fsdsdf");
        notification1.setMSender("Le Thai Son");
        notification1.setMTittle("Thong bao hop khan cap vinh");
        notification1.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification1);

        Notification notification2 = new Notification();
        notification2.setIsFavorite(false);
        notification2.setIsHot(false);
        notification2.setMAvata(R.drawable.p2);
        notification2.setMContent("Thong basdsd fsdsdf");
        notification2.setMSender("Le Thai Son");
        notification2.setMTittle("Thong bao hop khan cap");
        notification2.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification2);

        Notification notification3 = new Notification();
        notification3.setIsFavorite(true);
        notification3.setIsHot(false);
        notification3.setMAvata(R.drawable.p3);
        notification3.setMContent("Đã có lúc anh mong tim mình bé lại\n" +
                "Để nỗi nhớ em không thể nào thêm nữa\n" +
                "Đã có lúc anh mong ngừng thời gian trôi\n" +
                "Để những dấu yêu sẽ không phai mờ\n" +
                "\n" +
                "Nếu không hát lên nặng lòng da diết\n" +
                "Nếu không nói ra làm sao biết\n" +
                "Anh thương em\n" +
                "Anh sẽ nói em nghe những điều chưa bao giờ\n" +
                "\n" +
                "Bình minh khuất lấp sau màn đêm như nỗi lòng anh\n" +
                "Chất chứa lâu nay em đâu nào hay biết\n" +
                "Hoàng hôn tắt nắng hay vì anh không hiểu được em\n" +
                "Dập tan bao yêu dấu lụi tàn\n" +
                "\n" +
                "Cất tiếng hát nghe sao lòng nhẹ cơn sầu\n" +
                "Dẫu có chút vương, chút ân tình chôn giấu\n" +
                "Đếm những nhớ thương thầm lặng trên tay\n" +
                "Nghe sao buốt thêm, ướt đôi vai gầy.\n" +
                "\n" +
                "Nếu không hát lên nặng lòng da diết\n" +
                "Nếu không nói ra làm sao biết\n" +
                "Anh thương em\n" +
                "Anh sẽ nói em nghe những điều chưa bao giờ\n" +
                "\n" +
                "Bình minh khuất lấp sau màn đêm như nỗi lòng anh\n" +
                "Chất chứa lâu nay em đâu nào hay biết\n" +
                "Hoàng hôn tắt nắng hay vì anh không hiểu được em\n" +
                "Dập tan bao yêu dấu lụi tàn\n" +
                "\n" +
                "Vì anh câm nín chôn sâu yêu thương anh trao đến em,\n" +
                "Lặng nhìn em lướt qua bên đời.\n" +
                "Một mai ai biết cơn mê đưa em vào vòng tay mới.\n" +
                "Anh sẽ chờ phía sau giấc mơ của em\n" +
                "Anh sẽ chờ để nói những điều chưa bao giờ");
        notification3.setMSender("Le Thai Son");
        notification3.setMTittle("Thong bao hop khan cap");
        notification3.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification3);

        Notification notification4 = new Notification();
        notification4.setIsFavorite(false);
        notification4.setIsHot(false);
        notification4.setMAvata(R.drawable.p4);
        notification4.setMContent("Thong basdsd fsdsdfffgdfgdfgdf ");
        notification4.setMSender("Le Thai Son");
        notification4.setMTittle("Thong bao hop khan cap");
        notification4.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification4);

        Notification notification5 = new Notification();
        notification5.setIsFavorite(true);
        notification5.setIsHot(false);
        notification5.setMAvata(R.drawable.p2);
        notification5.setMContent("Thong basdsd ");
        notification5.setMSender("Le Thai Son");
        notification5.setMTittle("Thong bao hop khan cap");
        notification5.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification5);

        Notification notification6 = new Notification();
        notification6.setIsFavorite(true);
        notification6.setIsHot(false);
        notification6.setMAvata(R.drawable.p3);
        notification6.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification6.setMSender("Le Thai Son");
        notification6.setMTittle("Thong bao hop khan cap");
        notification6.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification6);

        Notification notification7 = new Notification();
        notification7.setIsFavorite(true);
        notification7.setIsHot(false);
        notification7.setMAvata(R.drawable.p1);
        notification7.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification7.setMSender("Le Thai Son");
        notification7.setMTittle("Thong bao hop khan cap");
        notification7.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification7);

        Notification notification8 = new Notification();
        notification8.setIsFavorite(true);
        notification8.setIsHot(false);
        notification8.setMAvata(R.drawable.p1);
        notification8.setMContent("Thong basdsd fsdsdfffgdfgdfgdf");
        notification8.setMSender("Le Thai Son");
        notification8.setMTittle("Thong bao hop khan cap");
        notification8.setMTime("14:32 PM, 06/10");
        mListNotifications.add(notification8);
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
}
