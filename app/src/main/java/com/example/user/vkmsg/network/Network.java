package com.example.user.vkmsg.network;

import com.example.user.vkmsg.parser.LongPollUpdatesDeserializer;
import com.example.user.vkmsg.parser.VkLongPollUpdates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitLongPoll;
    private static VKapi vKapi;
    private static OkHttpClient client;
    private static OkHttpClient clientLP;
    private static VKapiLP vKapiLP;
    private static HttpLoggingInterceptor loggingInterceptor;

    static {

        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        vKapi = mRetrofit.create(VKapi.class);
    }

    public static void setVkApiLp () {

        clientLP = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(70, TimeUnit.SECONDS)
                .readTimeout(70,TimeUnit.SECONDS).build();

        Gson customGson =  new GsonBuilder()
                .registerTypeAdapter(VkLongPollUpdates.class, new LongPollUpdatesDeserializer())
                .create();

        mRetrofitLongPoll = new Retrofit.Builder()
                .client(clientLP)
                .baseUrl("https://imv4.vk.com/")
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        vKapiLP = mRetrofitLongPoll.create(VKapiLP.class);
    }

    public static VKapiLP getvKapiLP() {
        return vKapiLP;
    }

    public static VKapi getvKapi() {
        return vKapi;
    }
}
