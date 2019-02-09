package com.example.user.vkmsg.mvp.model;

import android.graphics.Bitmap;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.models.MessageModel;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.models.modelGetConversation.Item;
import com.example.user.vkmsg.mvp.contracts.ChatRecyclerAdapterContract;
import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.utils.PhotoOperations;
import com.example.user.vkmsg.utils.SpecialModelMessages;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChatRecyclerAdapterModel implements ChatRecyclerAdapterContract.Model {
    RxBus bus;

    public ChatRecyclerAdapterModel (RxBus bus) {
        this.bus = bus;
    }

    public void loadData (int id) {
        getData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(data -> bus.send(data)).subscribe();
    }

    private Observable<MessageModel> getData(int id) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("photo_100");
        return Network.getvKapi()
                .getConversationById("100",  "0", String.valueOf(id), "1", fields, MyApp.token, "5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(container____ -> {
                    ArrayList<SpecialModelMessages> list = new ArrayList<>();
                    for (Item item: container____.getResponse().getItems()){
                        list.add(SpecialModelMessages.create(item, container____.getResponse().getProfiles()));
                    }
                    return Observable.fromIterable(list);
                })
                .map(this::parseData);
    }

    private MessageModel parseData (SpecialModelMessages modelMessages) {
        MessageModel model = new MessageModel();
        model.setMessageId(modelMessages.item.getId());
        model.setUser_name(modelMessages.profile.getFirstName() + " " + modelMessages.profile.getLastName());
        model.setPhoto_100(modelMessages.profile.getPhoto100());
        model.setText(modelMessages.item.getText());
        model.setFrom_id(modelMessages.item.getFromId());
        return model;
    }

    public Observable<Bitmap> getPic (String url) {
        return Observable
                .fromCallable(() -> PhotoOperations.getCroppedBitmap(Picasso.get().load(url).get()))
                .subscribeOn(Schedulers.io());

    }
}
