package com.example.user.vkmsg.mvp.presenter;


import android.os.Handler;

import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.mvp.model.ConversationFragmentModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ConversationFragmentPresenter implements ConversationFragmentContract.Presenter{
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
    public void attachView(ConversationFragmentContract.View mvpView) {
        view = mvpView;
        model = new ConversationFragmentModel(bus);
    }

    @Override
    public void viewIsReady() {
        model.downloadAppBar(new AppBar());
        view.initRecyclerView();

        bus.toObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter((o -> o instanceof AppBar)).subscribe(o -> {
            view.setAppBar((AppBar) o);
        });
    }

    @Override
    public void detachView() {
        view = null;
        model = null;
    }

    @Override
    public void destroy() {
    }
}
