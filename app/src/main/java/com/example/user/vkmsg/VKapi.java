package com.example.user.vkmsg;

import com.example.user.vkmsg.POJO.POJOConverdsations.Container_;
import com.example.user.vkmsg.POJO.POJOGetConversation.Container____;
import com.example.user.vkmsg.POJO.POJOGetLongPoll.Container__;
import com.example.user.vkmsg.POJO.POJOLongPollHistory.Container___;
import com.example.user.vkmsg.POJO.POJOUsers.Container;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
            @Query("count") String msgsCount,
            @Query("rev") String rev,
            @Query("peer_id") String chatId,
            @Query("extended") String extended,
            @Query("fields") List<String> fields,
            @Query("access_token") String token,
            @Query("v") String version);

}
