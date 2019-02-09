package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.POJO.MessageModel;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.interfaces.IView;
import com.example.user.vkmsg.mvp.contracts.ChatFragmentContract;
import com.example.user.vkmsg.mvp.contracts.ChatRecyclerAdapterContract;

import io.reactivex.disposables.CompositeDisposable;

public class ChatFragmentPresenter implements ChatFragmentContract.Presenter {
    private ChatFragmentContract.View view;


    @Override
    public void attachView(ChatFragmentContract.View mvpView) {
        view = mvpView;
    }

    @Override
    public void viewIsReady() {
        view.initRecycler();

    }

    @Override
    public void detachView() {
        data.clear();
        view = null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void attachAdapter(ChatFragmentAdapter adapter) {

    }
}
