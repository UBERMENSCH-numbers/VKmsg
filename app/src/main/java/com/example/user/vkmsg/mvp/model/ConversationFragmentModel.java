package com.example.user.vkmsg.mvp.model;

import android.util.Log;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.models.AppBar;
import com.example.user.vkmsg.models.modelUsers.Container;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.utils.PhotoOperations;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversationFragmentModel implements ConversationFragmentContract.Model {
    private Target target;
    private RxBus bus;

    public ConversationFragmentModel (RxBus bus) {
        Log.e("ConversationFragModel", "CREATED");
        this.bus = bus;
    }

    @Override
    public void downloadAppBar(AppBar appBar) {
        ArrayList<String> ids = new ArrayList<>();
        ids.add(MyApp.id);

        Network.getvKapi().getUser(ids, MyApp.token, "photo_max_orig", "5.92").enqueue(new Callback<Container>() {
            @Override
            public void onResponse(Call<Container> call, Response<Container> response) {
                com.example.user.vkmsg.models.modelUsers.Response response_ = response.body().getResponse().get(0);
                appBar.setUserName(response_.getFirstName() + " " + response_.getLastName());
                loadPic(response_.getPhotoMaxOrig(), appBar);
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {

            }
        });
    }

    void loadPic(String url, AppBar appBar) {
        Observable
                .fromCallable(() -> PhotoOperations.getCroppedBitmap(Picasso.get().load(url).get()))
                .doOnNext(bitmap -> {
                    appBar.setPicture(bitmap);
                    bus.send(appBar);
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }



}
