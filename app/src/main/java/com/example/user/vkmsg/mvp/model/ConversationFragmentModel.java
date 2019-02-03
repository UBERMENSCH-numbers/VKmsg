package com.example.user.vkmsg.mvp.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.user.vkmsg.ConversationsFragment;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.Network;
import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.POJO.POJOUsers.Container;
import com.example.user.vkmsg.mvp.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversationFragmentModel implements ConversationFragmentContract.Model {
    private Target target;
    private RxBus bus;

    public ConversationFragmentModel (RxBus bus) {
        this.bus = bus;
    }

    @Override
    public void downloadAppBar(AppBar appBar) {
        ArrayList<String> ids = new ArrayList<>();
        ids.add(MyApp.id);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                appBar.setPicture(bitmap);
                bus.send(appBar);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}};

        Network.getvKapi().getUser(ids, MyApp.token, "photo_200", "5.92").enqueue(new Callback<Container>() {
            @Override
            public void onResponse(Call<Container> call, Response<Container> response) {
                com.example.user.vkmsg.POJO.POJOUsers.Response response_ = response.body().getResponse().get(0);
                appBar.setUserName(response_.getFirstName() + " " + response_.getLastName());
                loadPic(response_.getPhoto200(), target);
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {

            }
        });


    }

    void loadPic(String url, Target target) {
        Picasso.get()
                .load(url)
                .placeholder(com.example.user.vkmsg.R.drawable.holder)
                .into(target);
    }



}
