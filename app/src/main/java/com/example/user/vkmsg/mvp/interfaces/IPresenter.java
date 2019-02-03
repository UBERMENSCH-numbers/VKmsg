package com.example.user.vkmsg.mvp.interfaces;

public interface IPresenter<V extends IView> {

    void attachView(V mvpView);

    void viewIsReady();

    void detachView();

    void destroy();
}
