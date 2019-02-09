package com.example.user.vkmsg.mvp.presenter;


import com.example.user.vkmsg.models.AppBar;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.mvp.model.ConversationFragmentModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ConversationFragmentPresenter implements ConversationFragmentContract.Presenter{
    private ConversationFragmentContract.View view;
    private ConversationFragmentContract.Model model;
    private RxBus bus;
    private CompositeDisposable observable;

    public ConversationFragmentPresenter(RxBus bus) {
        this.bus = bus;
        observable = new CompositeDisposable();
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
        observable.add(bus.toObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter((o -> o instanceof AppBar)).subscribe(o -> {
            view.setAppBar((AppBar) o);
        }));
    }

    @Override
    public void detachView() {
        view = null;
        model = null;
        observable.clear();
    }

    @Override
    public void destroy() {
    }
}
