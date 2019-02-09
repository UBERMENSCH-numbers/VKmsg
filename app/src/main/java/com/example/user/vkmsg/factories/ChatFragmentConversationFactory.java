package com.example.user.vkmsg.factories;

import com.example.user.vkmsg.mvp.presenter.ChatFragmentPresenter;

public class ChatFragmentConversationFactory<T> implements IPresenterFactory<T> {
    @Override
    public T create() {
        return (T) new ChatFragmentPresenter();
    }
}
