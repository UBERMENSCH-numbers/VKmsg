package com.example.user.vkmsg.mvp.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.Network;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.example.user.vkmsg.mvp.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationRecyclerAdapterContract;
import com.example.user.vkmsg.utils.SpecialModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;

public class ConversationRecyclerModel implements ConversationRecyclerAdapterContract.Model {
    private RxBus rxBus;
    private int size;
    private int i = 0;

    public ConversationRecyclerModel (RxBus rxBus) {
        this.rxBus = rxBus;
    }

    public void loadConversations () {
        getConversationsFromNetwork()
                .subscribe(recyclerItem -> loadPic(recyclerItem.getPhotoUrl(), getTarger(recyclerItem)));
    }

    private Observable<RecyclerItem> getConversationsFromNetwork() {

        ArrayList<String> fields = new ArrayList<>();
        fields.add("photo_100");

        return Network.getvKapi().getConversations(MyApp.token,"1","all", fields,"5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(container_ -> {
                    size = container_.getResponse().getItems().size();
                    return io.reactivex.Observable.just(container_.getResponse());
                })
                .flatMap(response -> Observable.zip(Observable.fromIterable(response.getItems()),
                        Observable.fromIterable(response.getProfiles()), (a,b) -> SpecialModel.create(a , response.getProfiles()))
                        .map(this::parseData));
    }

    private void loadPic (String url, Target target) {
        Picasso.get().load(url).into(target);
    }

    private Target getTarger (RecyclerItem recyclerItem) {
        return new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                i ++;
                recyclerItem.setConversationPic(bitmap);
                Log.e("Bus", "SEND Item");
                rxBus.send(recyclerItem);
                if (i == size) rxBus.complete();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        };
    }

    private RecyclerItem parseData (SpecialModel itemProfilePair) {
        RecyclerItem recyclerItem = new RecyclerItem();
        recyclerItem.setLastMsg(itemProfilePair.item.getLastMessage().getText());
        recyclerItem.setChatId(itemProfilePair.item.getConversation().getPeer().getId());

        if (itemProfilePair.item.getConversation().getPeer().getType().equals("user")) {
            recyclerItem.setConversationTitle(itemProfilePair.profile.getFirstName() + " " +itemProfilePair.profile.getLastName());
            recyclerItem.setPhotoUrl(itemProfilePair.profile.getPhoto100());
        } else {
            recyclerItem.setConversationTitle(itemProfilePair.item.getConversation().getChatSettings().getTitle());
            recyclerItem.setPhotoUrl(itemProfilePair.item.getConversation().getChatSettings().getPhoto().getPhoto100());
        }
        return recyclerItem;
    }

}
