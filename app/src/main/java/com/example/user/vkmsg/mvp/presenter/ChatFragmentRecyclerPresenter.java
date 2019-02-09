package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.POJO.MessageModel;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ChatRecyclerAdapterContract;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class ChatFragmentRecyclerPresenter implements ChatRecyclerAdapterContract.Presenter {
    private RxBus bus;
    private ChatRecyclerAdapterContract.Model model;
    private ArrayList<MessageModel> data;
    private CompositeDisposable observable;
    private ChatFragmentAdapter adapter;
    private int chatId;

    public ChatFragmentRecyclerPresenter (RxBus rxBus, ChatRecyclerAdapterContract.Model model, int chatId) {
        this.bus = rxBus;
        this.model = model;
        this.chatId = chatId;
    }

    @Override
    public void attachAdapter(ChatFragmentAdapter adapter) {
        this.adapter = adapter;
        model.loadData(chatId);

        observable.add(bus.toObservable().filter(o -> o instanceof MessageModel)
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        data.add((MessageModel) o);
                        adapter.notifyItemInserted(data.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, ChatRecyclerAdapterContract.View rowView) {
        rowView.setMessageText(data.get(position).getText());
        rowView.setName(data.get(position).getUser_name());
        rowView.setFromId(data.get(position).getFrom_id());
        rowView.setAvatar();
    }

    @Override
    public int getRepositoriesRowsCount() {
        return data.size();
    }

    @Override
    public void detach() {
        observable.clear();
    }

    @Override
    public int getLayoutType(int position) {
        if  (String.valueOf(data.get(position).getFrom_id()).equals(MyApp.id)) return 1;
        return 2;
    }
}