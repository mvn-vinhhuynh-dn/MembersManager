package com.asiantech.membersmanager.models;

import lombok.Data;

/**
 *
 * Created by xuanphu on 06/10/2015.
 */
@Data
public class Notification {
    int mAvata;
    String mSender, mTittle, mContent, mTime;
    Boolean isFavorite,isHot,isCheck;
}
