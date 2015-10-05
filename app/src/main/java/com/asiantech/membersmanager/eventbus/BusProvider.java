package com.asiantech.membersmanager.eventbus;

import com.squareup.otto.Bus;

/**
 * @Create by mr.Tran 10/02/2015
 */
public class BusProvider {
    private static Bus mBus;

    public BusProvider() {
    }

    public static synchronized Bus getInstanceNew() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }

}
