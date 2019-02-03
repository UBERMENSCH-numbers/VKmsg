package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.ConversationsFragment;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.mvp.contracts.MainActivityContract;
import com.example.user.vkmsg.mvp.model.MainActivityModel;
import com.example.user.vkmsg.mvp.view.BaseFragment;
import com.example.user.vkmsg.mvp.interfaces.IView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKError;

public class MainActivityPresenter extends BasePresenter implements MainActivityContract.Presenter {
    MainActivityContract.View view;
    MainActivityContract.Model model;

    public MainActivityPresenter (MainActivityModel model) {
        this.model = model;
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void login() {
        String token = model.loadPreferences("token");
        String id = model.loadPreferences("id");
        if (token == null || token.trim().length() < 1 || id == null || id.trim().length() < 1) {
            view.VkSdkLogin();
        } else {
            MyApp.id = id;
            MyApp.token = token;
            addFragment(new ConversationsFragment());
        }

    }

    @Override
    public void onLoginResult(VKAccessToken res) {
        model.savePreferences(res.accessToken, "token");
        model.savePreferences(res.userId, "id");
        MyApp.id = res.userId;
        MyApp.token = res.accessToken;
        addFragment(new ConversationsFragment());
    }

    @Override
    public void onLoginError(VKError error) {
        view.showToast("Auth Error");
    }

    @Override
    public void attachView(IView mvpView) {
        super.attachView(mvpView);
        view = (MainActivityContract.View) mvpView;
    }


    @Override
    public void addFragment(BaseFragment fragment) {
        view.setFragment(fragment);
    }
}
