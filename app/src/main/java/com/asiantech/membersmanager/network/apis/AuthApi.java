package com.asiantech.membersmanager.network.apis;

import com.asiantech.membersmanager.models.Items;
import com.asiantech.membersmanager.network.core.ApiClient;
import com.asiantech.membersmanager.network.core.Callback;

import retrofit.RetrofitError;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by tientun on 7/27/15.
 */
public class AuthApi {
    public static void getAllVideo(String playlistId, String part, int numItem, String type, String key, final Callback<Items> callback) {
        ApiClient.call().getAllVideo(playlistId, part, numItem, type, key, new Callback<Items>() {
            @Override
            public void success(Items items) {
                callback.success(items);
            }

            @Override
            public void failure(RetrofitError error, com.asiantech.membersmanager.network.Error myError) {
                callback.failure(error, myError);
            }

        });
    }
}
