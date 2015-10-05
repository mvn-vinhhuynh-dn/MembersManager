package com.asiantech.membersmanager.network;

import com.asiantech.membersmanager.models.BaseModel;
import com.asiantech.membersmanager.network.core.Callback;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


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

}
