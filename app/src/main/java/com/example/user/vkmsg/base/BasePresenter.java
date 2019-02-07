package com.example.user.vkmsg.base;

import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.interfaces.IView;

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }

}
