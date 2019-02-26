package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.models.MessageModel;
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
    private int chatId;
    private Boolean isLoadingFlag = false;

    public ChatFragmentRecyclerPresenter (RxBus rxBus, ChatRecyclerAdapterContract.Model model, int chatId) {
        this.bus = rxBus;
        this.model = model;
        this.chatId = chatId;
        observable = new CompositeDisposable();
        data = new ArrayList<>();
    }

    @Override
    public void attachAdapter(ChatFragmentAdapter adapter) {
        model.loadData(chatId, 0);

        observable.add(bus.toObservable().filter(o -> o instanceof MessageModel)
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        if (isLoadingFlag) isLoadingFlag = false;
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
        if (position == data.size() - 1 && !isLoadingFlag) loadMoreMessages(chatId, data.size());

        if (getLayoutType(position) == 2) {
            rowView.setAvatar(model.getPic(data.get(position).getPhoto_100()).retry(2).blockingFirst());
            rowView.setName(data.get(position).getUser_name());
        }
        rowView.setFromId(data.get(position).getFrom_id());
        rowView.setMessageText(data.get(position).getText());

    }

    @Override
    public int getRepositoriesRowsCount() {
        return data.size();
    }

    @Override
    public void detach() {
        observable.clear();
        model = null;
        observable = null;
        bus = null;
    }

    @Override
    public int getLayoutType(int position) {
        if  (String.valueOf(data.get(position).getFrom_id()).equals(MyApp.id)) return 1;
        return 2;
    }

    void loadMoreMessages (int id,int offset) {
        model.loadData(id, offset);

    }

}
