package com.example.user.vkmsg;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.vk.sdk.VKSdk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private Retrofit mRetrofit;
    private static VKapi VKapi;

    @Override

    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        VKapi = mRetrofit.create(VKapi.class);
    }

    public static VKapi getVKapi () {
        return VKapi;
    }

}
