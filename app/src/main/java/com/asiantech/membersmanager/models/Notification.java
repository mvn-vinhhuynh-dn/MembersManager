package com.asiantech.membersmanager.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * Created by xuanphu on 06/10/2015.
 */
@Data
public class Notification implements Serializable {
    private int mAvata;
    private  String mSender, mTittle, mContent, mTime;
    private Boolean isFavorite, isHot, isCheck;
}
