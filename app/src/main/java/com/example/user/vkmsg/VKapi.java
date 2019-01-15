package com.example.user.vkmsg;

import com.example.user.vkmsg.POJO.POJOConverdsations.Container_;
import com.example.user.vkmsg.POJO.POJOUsers.Container;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKapi {
    @GET("method/users.get")
    Observable<Container> getUser (
            @Query("user_ids") String user_ids,
            @Query("access_token") String token,
            @Query("fields") String field,
            @Query("v") String version);

    @GET("method/messages.getConversations")
    Observable<Container_> getConversations (
            @Query("access_token") String token,
            @Query("extended") String extended,
            @Query("filter") String filter,
            @Query("fields") List<String> fields,
            @Query("v") String version);

}
