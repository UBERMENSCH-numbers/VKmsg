package com.example.user.vkmsg.factories;

import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.mvp.model.ConversationFragmentModel;
import com.example.user.vkmsg.mvp.presenter.ConversationFragmentPresenter;

public class ConversationFragmentPresenterFactory<T> implements IPresenterFactory<T> {
    private RxBus rxBus;

    public ConversationFragmentPresenterFactory (RxBus rxBus) {
        this.rxBus = rxBus;
    }

    @Override
    public T create() {
        return (T) new ConversationFragmentPresenter(rxBus, new ConversationFragmentModel(rxBus));
    }
}
