package com.example.user.vkmsg.mvp.presenter;

import android.util.Log;

import com.example.user.vkmsg.ConversationRecyclerAdapter;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.example.user.vkmsg.mvp.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationRecyclerAdapterContract;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ConversationRecyclerPresenter implements ConversationRecyclerAdapterContract.Presenter { //TODO: refactor this
    private ConversationRecyclerAdapterContract.Model model;
    private ArrayList<RecyclerItem> data = new ArrayList<>();
    private ConversationRecyclerAdapter adapter;

    public ConversationRecyclerPresenter(ConversationRecyclerAdapterContract.Model model, RxBus bus) {
        this.model = model;
        registerBus(bus);
        model.loadConversations();
    }

    public void onBindRepositoryRowViewAtPosition(int position, ConversationRecyclerAdapterContract.View rowView) {
        rowView.setId(data.get(position).getChatId());
        rowView.setLastMsg(data.get(position).getLastMsg());
        rowView.setTitle(data.get(position).getConversationTitle());
        rowView.setImageView(data.get(position).getConversationPic());

    }

    public void attachAdapter(ConversationRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    public int getRepositoriesRowsCount() {
        return data.size();
    }

    void registerBus (RxBus bus) {
        bus.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter((o -> o instanceof RecyclerItem))
                .doOnComplete(() -> {
                    adapter.notifyDataSetChanged();
                    Log.e("LOG", "complete");
                })
                .subscribe(item -> data.add((RecyclerItem) item));

    }

}