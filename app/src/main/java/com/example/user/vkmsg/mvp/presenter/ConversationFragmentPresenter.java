package com.example.user.vkmsg.mvp.presenter;


import android.view.View;

import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.mvp.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.mvp.interfaces.IView;
import com.example.user.vkmsg.mvp.model.ConversationFragmentModel;

import io.reactivex.functions.Consumer;

public class ConversationFragmentPresenter implements ConversationFragmentContract.Presenter {
    private ConversationFragmentContract.View view;
    private ConversationFragmentContract.Model model;
    private RxBus bus;

    public ConversationFragmentPresenter(RxBus bus) {
        this.bus = bus;
    }

    @Override
    public void onClick (int position, boolean isLongClick, int chatId) {

    }

    @Override
    public void attachView(IView mvpView) {
        view = (ConversationFragmentContract.View) mvpView;
        model = new ConversationFragmentModel(bus);
    }

    @Override
    public void viewIsReady() {
        model.downloadAppBar(new AppBar());
        bus.toObservable().filter((o -> o instanceof AppBar)).subscribe(o -> {
            view.setAppBar((AppBar) o);
        });
        view.initRecyclerView();
    }

    @Override
    public void detachView() {
        view = null;
        model = null;
        bus = null;
    }

    @Override
    public void destroy() {

    }
}
