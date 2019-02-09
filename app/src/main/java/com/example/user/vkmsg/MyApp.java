package com.example.user.vkmsg;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.user.vkmsg.models.modelLongPollHistory.Response;
import com.vk.sdk.VKSdk;

public class MyApp extends Application {
    public static String token;
    public static String id;
    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
        VKSdk.initialize(getApplicationContext());
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("request"));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Response response = (Response) intent.getSerializableExtra("response");
            Log.e("Response", String.valueOf(response.getHistory().size()));
        }
    };

    public RxBus bus() {
        return bus;
    }

}
