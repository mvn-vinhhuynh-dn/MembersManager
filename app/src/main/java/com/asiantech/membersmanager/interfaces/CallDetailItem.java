package com.asiantech.membersmanager.interfaces;

import com.asiantech.membersmanager.models.Notification;

import java.util.ArrayList;

/**
 * Created by xuanphu on 07/10/2015.
 */
public interface CallDetailItem {
    void OnCallDetails(ArrayList<Notification> arrayList, int position);
}
