package com.example.user.vkmsg.mvp.presenter;

import com.example.user.vkmsg.base.BasePresenter;
import com.example.user.vkmsg.fragments.ConversationsFragment;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.mvp.contracts.FragmentNavigationContract;
import com.example.user.vkmsg.mvp.contracts.MainActivityContract;
import com.example.user.vkmsg.base.BaseNavigationFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKError;

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View> implements
        MainActivityContract.Presenter{

    MainActivityContract.View view;
    MainActivityContract.Model model;

    public MainActivityPresenter (MainActivityContract.Model model) {
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
            if (!view.hasFragment()) addFragment(new ConversationsFragment());
        }
    }

    @Override
    public void onLoginResult(VKAccessToken res) {
        model.savePreferences(res.accessToken, "token");
        model.savePreferences(res.userId, "id");
        MyApp.id = res.userId;
        MyApp.token = res.accessToken;
        if (!view.hasFragment()) addFragment(new ConversationsFragment());
    }

    @Override
    public void onLoginError(VKError error) {
        view.showToast("Auth Error");
    }

    @Override
    public void attachView(MainActivityContract.View mvpView) {
        super.attachView(mvpView);
        view = mvpView;
    }

    @Override
    public void addFragment(BaseNavigationFragment fragment) {
        view.setFragment(fragment);
    }
}

