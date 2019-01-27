package com.example.user.vkmsg;

import com.alibaba.fastjson.JSON;
import com.example.user.vkmsg.POJO.POJOConverdsations.Container_;
import com.example.user.vkmsg.POJO.POJOGetLongPoll.Container__;
import com.example.user.vkmsg.POJO.POJOGetLongPollReq.Container_____;
import com.example.user.vkmsg.POJO.POJOLongPollHistory.Container___;
import com.example.user.vkmsg.POJO.POJOUsers.Container;
import com.example.user.vkmsg.Parser.VkLongPollUpdates;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface VKapiLP {
    @GET("{server}")
    Call<VkLongPollUpdates> sendLongPoll(
            @Path("server") String server,
            @Query("act") String a_check,
            @Query("key") String key,
            @Query("ts") String ts,
            @Query("wait") String wait,
            @Query("mode") String mode,
            @Query("version") String version);
}
