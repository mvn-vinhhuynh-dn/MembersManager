package com.asiantech.membersmanager.models;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by xuanphu on 06/10/2015.
 */
@Data
public class Notification implements Serializable {
    private int mAvata;
    private String mSender;
    private String mTittle;
    private String mContent;
    private String mTime;
    private Boolean isChecked = false;
    private Boolean isFavorite;
    private Boolean isHot;
}
