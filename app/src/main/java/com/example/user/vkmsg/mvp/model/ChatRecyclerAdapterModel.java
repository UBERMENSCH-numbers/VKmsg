package com.example.user.vkmsg.mvp.model;

import android.util.Log;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.POJO.MessageModel;
import com.example.user.vkmsg.POJO.POJOGetConversation.Item;
import com.example.user.vkmsg.POJO.POJOGetConversation.Profile;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ChatRecyclerAdapterContract;
import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.utils.SpecialModelMessages;

import java.util.ArrayList;

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
        return Network.getvKapi()
                .getConversationById("100",  "0", String.valueOf(id), "1", fields, MyApp.token, "5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(container____ -> Observable.zip(Observable.fromIterable(container____.getResponse().getItems()),
                        Observable.fromIterable(container____.getResponse().getProfiles()),
                        (a, b) -> SpecialModelMessages.create(a, container____.getResponse().getProfiles())))
                .map(this::parseData);
    }

    private MessageModel parseData (SpecialModelMessages modelMessages) {
        MessageModel model = new MessageModel();
        model.setMessageId(modelMessages.item.getId());
        model.setPhoto_100(modelMessages.profile.getPhoto100());
        model.setText(modelMessages.item.getText());
        model.setUser_name(modelMessages.profile.getFirstName() + " " + modelMessages.profile.getLastName());
        model.setFrom_id(modelMessages.item.getFromId());
        return model;
    }



}
