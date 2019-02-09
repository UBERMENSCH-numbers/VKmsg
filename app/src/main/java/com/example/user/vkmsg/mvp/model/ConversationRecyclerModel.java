package com.example.user.vkmsg.mvp.model;

import android.graphics.Bitmap;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.models.RecyclerItem;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationRecyclerAdapterContract;
import com.example.user.vkmsg.utils.PhotoOperations;
import com.example.user.vkmsg.utils.SpecialModelConversation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;


public class ConversationRecyclerModel implements ConversationRecyclerAdapterContract.Model {
    private RxBus rxBus;

    public ConversationRecyclerModel (RxBus rxBus) {
        this.rxBus = rxBus;
    }

    public void loadConversations() {

        ArrayList<String> fields = new ArrayList<>();
        fields.add("photo_100");

        Network.getvKapi().getConversations(MyApp.token,"1","all", fields,"5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(container_ -> Observable.just(container_.getResponse()))
                .flatMap(response -> Observable.zip(Observable.fromIterable(response.getItems()),
                        Observable.fromIterable(response.getProfiles()), (a, b) -> SpecialModelConversation.create(a, response.getProfiles()))
                        .map(this::parseData))
                .doOnNext((recyclerItem) -> rxBus.send(recyclerItem))
//                .doOnComplete(() -> rxBus.onComplete())
                .subscribe();
    }

    private RecyclerItem parseData (SpecialModelConversation itemProfilePair) {
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

    public Observable<Bitmap> getPic (String url) {
        return Observable
                .fromCallable(() -> PhotoOperations.getCroppedBitmap(Picasso.get().load(url).get()))
                .subscribeOn(Schedulers.io());

    }

}
