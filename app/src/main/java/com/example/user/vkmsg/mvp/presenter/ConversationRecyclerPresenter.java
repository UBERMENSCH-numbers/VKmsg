package com.example.user.vkmsg.mvp.presenter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import com.example.user.vkmsg.ConversationRecyclerAdapter;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationRecyclerAdapterContract;
import com.example.user.vkmsg.utils.PhotoOperations;
import com.example.user.vkmsg.utils.SpecialModel;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogRecord;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ConversationRecyclerPresenter implements ConversationRecyclerAdapterContract.Presenter {
    private ConversationRecyclerAdapterContract.Model model;
    private ArrayList<RecyclerItem> data;
    private ConversationRecyclerAdapter adapter;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private RxBus rxBus;

    public ConversationRecyclerPresenter(ConversationRecyclerAdapterContract.Model model, RxBus bus) {
        this.model = model;
        this.rxBus = bus;
        data = new ArrayList<>();

    }

    public void onBindRepositoryRowViewAtPosition(int position, ConversationRecyclerAdapterContract.View rowView) {
        rowView.setId(data.get(position).getChatId());
        rowView.setLastMsg(data.get(position).getLastMsg());
        rowView.setTitle(data.get(position).getConversationTitle());
        try {
            rowView.setImageView(model.getPic(data.get(position).getPhotoUrl()).blockingFirst());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void attachAdapter(ConversationRecyclerAdapter adapter) {
        this.adapter = adapter;
        model.loadConversations();
        registerBus();
    }

    @Override
    public void detach() {
        Log.e("LOG", "detach");
        rxBus.unsubscribe();
        disposables.clear();
        adapter = null;
        data = null;
        model = null;
    }

    public int getRepositoriesRowsCount() {
        return data.size();
    }

    void registerBus () {
        disposables.add(rxBus.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter((o -> o instanceof RecyclerItem))
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Object o) {
                        data.add((RecyclerItem) o);
                        adapter.notifyItemInserted(data.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }));
    }

}