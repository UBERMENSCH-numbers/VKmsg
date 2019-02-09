package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.mvp.contracts.ChatFragmentContract;


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
        view = null;
    }

    @Override
    public void destroy() {

    }
}
