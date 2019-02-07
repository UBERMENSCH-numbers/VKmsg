package com.example.user.vkmsg.factories;

import com.example.user.vkmsg.mvp.contracts.MainActivityContract;
import com.example.user.vkmsg.mvp.presenter.MainActivityPresenter;

public class MainActivityPresenterFactory<T> implements IPresenterFactory<T> {
    MainActivityContract.Model model;

    public MainActivityPresenterFactory (MainActivityContract.Model model) {
        this.model = model;
    }

    @Override
    public T create() {
        return (T) new MainActivityPresenter(model);
    }
}
