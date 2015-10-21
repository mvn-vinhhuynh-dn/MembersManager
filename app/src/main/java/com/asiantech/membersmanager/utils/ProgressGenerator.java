package com.asiantech.membersmanager.utils;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

import java.util.Random;

public class ProgressGenerator {
    public static final String PLAYLIST_KEY = "PLdYSWqTrWP2jyqWIdjsATbrb11uN_BMrF";
    public static final int MAX_ITEMS = 41;
    public static final String ITEM_TYPE = "video";
    public static final String API_SNIPPET = "snippet";
    private static final String API_KEY = "IzaSyBtEYk0NLi1DcEjYx8Z1TDDWulmmTajV4s";
    private String a;

    public interface OnCompleteListener {
        void onComplete();

        void onFail();
    }

    private OnCompleteListener mListener;
    private int mProgress = 0;

    public ProgressGenerator(OnCompleteListener listener, String A) {
        mListener = listener;
        a = A;
    }

//    Handler mHandler = new Handler();
//    Runnable mRunable;

    public void start(final ProcessButton button) {
//        mRunable = new Runnable() {
//            @Override
//            public void run() {
//                mProgress++;
//                button.setProgress(mProgress);
//            }
//        };
//        mHandler.postDelayed(mRunable, 1000);
//
//        AuthApi.getAllVideo(PLAYLIST_KEY, API_SNIPPET, MAX_ITEMS, ITEM_TYPE, a + API_KEY, new Callback<Items>() {
//            @Override
//            public void success(Items item) {
//                mListener.onComplete();
//                mHandler.removeCallbacks(mRunable);
//            }
//
//            @Override
//            public void failure(RetrofitError error, com.asiantech.membersmanager.network.Error myError) {
//                mListener.onFail();
//                mHandler.removeCallbacks(mRunable);
//                button.setProgress(0);
//            }
//        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    handler.postDelayed(this, generateDelay());
                } else {
                    mListener.onComplete();
                }
            }
        }, generateDelay());
    }

    private Random random = new Random();

    private int generateDelay() {
        return random.nextInt(1000);
    }
}
