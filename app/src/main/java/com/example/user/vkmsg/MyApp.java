package com.example.user.vkmsg;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private Retrofit mRetrofit;
    private static VKapi VKapi;
    @Override

    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VKapi = mRetrofit.create(VKapi.class);
    }

    public static VKapi getVKapi () {
        return VKapi;
    }

}
