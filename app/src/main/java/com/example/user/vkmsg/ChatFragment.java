package com.example.user.vkmsg;


import android.content.Context;
import android.mtp.MtpConstants;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkmsg.POJO.MessageModel;
import com.example.user.vkmsg.POJO.POJOGetConversation.Item;
import com.example.user.vkmsg.POJO.POJOGetConversation.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.vk.sdk.VKUIHelper.getApplicationContext;
import static io.reactivex.Observable.fromIterable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private int chat_id;
    private MainActivity activity;
    private RecyclerView recyclerView;
    public ChatFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversation, container, false);

        if (this.getArguments() != null) {
            Bundle bundle = this.getArguments();
            chat_id = bundle.getInt("id");
        }

        ArrayList<MessageModel> chatAdapterDataset = new ArrayList<>();
        recyclerView = view.findViewById(R.id.messages_recycler);
        ChatAdapter chatAdapter = new ChatAdapter(chatAdapterDataset);
        recyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

        ArrayList<String> fields = new ArrayList<>();
        Network.getvKapi().getConversationById("100","0", String.valueOf(chat_id), "1", fields, MyApp.token , "5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(container____ -> {
                    ArrayList<Integer> profile_ids = new ArrayList<>();
                    ArrayList<MessageModel> messageModels = new ArrayList<>();

                    for (Profile profile : container____.getResponse().getProfiles())
                        profile_ids.add(profile.getId());

                    for (Item item : container____.getResponse().getItems()) {
                        MessageModel messageModel = new MessageModel();
                        messageModel.setFrom_id(item.getFromId());
                        messageModel.setText(item.getText());
                        messageModel.setMessageId(item.getId());
                        messageModel.setPhoto_100(container____.getResponse().getProfiles()
                                .get(profile_ids.indexOf(item.getFromId())).getPhoto100());
                        messageModels.add(messageModel);
                    }

                    return Observable.fromIterable(messageModels);
                }).doOnNext(chatAdapterDataset::add)
                .doOnComplete(chatAdapter::notifyDataSetChanged)
                .subscribe();

        return view;
    }


}
