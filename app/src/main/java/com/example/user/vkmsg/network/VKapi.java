package com.example.user.vkmsg.network;

import com.example.user.vkmsg.models.modelConversations.Container_;
import com.example.user.vkmsg.models.modelGetConversation.Container____;
import com.example.user.vkmsg.models.modelGetLongPoll.Container__;
import com.example.user.vkmsg.models.modelLongPollHistory.Container___;
import com.example.user.vkmsg.models.modelUsers.Container;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKapi {
    @GET("method/users.get")
    Observable<Container> getUserObservable (
            @Query("user_ids") ArrayList<String> ids,
            @Query("access_token") String token,
            @Query("fields") String field,
            @Query("v") String version);

    @GET("method/users.get")
    Call<Container> getUser (
            @Query("user_ids") ArrayList<String> ids,
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

    @GET("method/messages.getLongPollServer")
    Call<Container__> getLongPollServer (
            @Query("need_pts") String pts,
            @Query("lp_version") String longPolVer,
            @Query("access_token") String token,
            @Query("v") String version);

    @GET("method/messages.getLongPollHistory")
    Observable<Container___> getLongPollHisory (
            @Query("ts") String ts,
            @Query("pts") String pts,
            @Query("onlines") Boolean onlines,
            @Query("fields") List<String> fields,
            @Query("events_limit") String eventsLim,
            @Query("msg_limit") String msgLim,
            @Query("access_token") String token,
            @Query("v") String version);

    @GET("method/messages.getHistory")
    Observable<Container____> getConversationById (
            @Query("offset") String offset,
            @Query("count") String msgsCount,
            @Query("rev") String rev,
            @Query("peer_id") String chatId,
            @Query("extended") String extended,
            @Query("fields") List<String> fields,
            @Query("access_token") String token,
            @Query("v") String version);

}
