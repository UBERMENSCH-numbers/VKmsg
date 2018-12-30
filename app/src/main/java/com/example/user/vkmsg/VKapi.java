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
    @GET("method/users.get")
    Call<Container> getUser (
            @Query("user_ids") String user_ids,
            @Query("access_token") String token,
            @Query("fields") String field,
            @Query("v") String version);
}
