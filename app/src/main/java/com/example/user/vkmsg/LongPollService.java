package com.example.user.vkmsg;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.network.VKapi;
import com.example.user.vkmsg.network.VKapiLP;
import com.example.user.vkmsg.models.modelGetLongPoll.Container__;
import com.example.user.vkmsg.parser.NewMessageAdd;
import com.example.user.vkmsg.parser.VkLongPollUpdates;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LongPollService extends Service {
    private VKapi vKapi;
    private VKapiLP vKapiLP;
    private String ts;
    private String pts;
    private String key;
    private String server;

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        vKapi = Network.getvKapi();
        Network.setVkApiLp();
        vKapiLP = Network.getvKapiLP();
        vKapi.getLongPollServer("0", "2" , MyApp.token ,"5.92").enqueue(new Callback<Container__>() {
            @Override
            public void onResponse(Call<Container__> call, Response<Container__> response) {
                if (response.isSuccessful()) {
                    com.example.user.vkmsg.models.modelGetLongPoll.Response response_ = response.body().getResponse();
                    ts = response_.getTs().toString();
                    key = response_.getKey();
                    server = response_.getServer();
                    Thread thread = new Thread(() -> start());
                    thread.start();
                }
            }

            @Override
            public void onFailure(Call<Container__> call, Throwable t) {

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    void send (List<List<Integer>> updates) {
//        Intent intent = new Intent("request");
//        intent.putExtra("response", updates);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);}


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LOG", "SERVICE DESTROYED");
    }


    private void start () {
        while (true) {
            Log.e("Log", "Tic");
            try {
                VkLongPollUpdates response = vKapiLP.sendLongPoll(server.split("/")[1],
                        "a_check", key, ts, "60", "10", "3").execute().body();
                if (response != null) {
                    for (NewMessageAdd message : response.newMessageAddList)
                    Log.e("TAG", message.text);
                    ts = String.valueOf(response.ts);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

