package com.asiantech.membersmanager.network;

import com.asiantech.membersmanager.models.BaseModel;
import com.asiantech.membersmanager.models.Items;
import com.asiantech.membersmanager.network.core.Callback;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Copyright © 2015 AsianTech inc.
 * Created by asiantech on 8/10/15.
 */
public interface Api {

    @FormUrlEncoded
    @POST("/createUser")
    void signup(@Field(Parameter.NAME) String name,
                @Field(Parameter.EMAIL) String email,
                @Field(Parameter.PASSWORD) String password,
                Callback<BaseModel> callback);

    @GET("/playlistItems")
    void getAllVideo(@Query("playlistId") String playlistId,
                     @Query("part") String part,
                     @Query("maxResults") int numItem,
                     @Query("type") String type,
                     @Query("key") String key, Callback<Items> callback);
}
