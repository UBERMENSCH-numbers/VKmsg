package com.example.user.vkmsg.network;

import com.example.user.vkmsg.parser.VkLongPollUpdates;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
