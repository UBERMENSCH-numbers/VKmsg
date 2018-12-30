package com.example.user.vkmsg;

import com.example.user.vkmsg.POJO.Container;
import com.example.user.vkmsg.POJO.Response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VKapi {
    @GET("/method/users.get")
    public Call<Container> getUser(@Query("user_ids") String userId,
                                   @Query("access_token") String access_token,
                                   @Query("v") String version);
}