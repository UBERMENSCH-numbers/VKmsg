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
    Boolean isLoggedIn = false;
    MainActivityContract.View view;
    MainActivityContract.Model model;

    public MainActivityPresenter (MainActivityContract.Model model) {
        this.model = model;
    }

    @Override
    public void viewIsReady() {
        if (!isLoggedIn) {
            login();
        }
    }

    @Override
    public void login() {
        String token = "5c15c2d83e96e77b5b5f893fb1cfc8da552ab62b23f0197027b61e7894b7fb02b0af97ff18445d1072866";//model.loadPreferences("token"); DEBUG!!!
        String id = "152285219";//model.loadPreferences("id"); DEBUG!!!
        if (token == null || token.trim().length() < 1 || id == null || id.trim().length() < 1) {
            view.VkSdkLogin();
        } else {
            MyApp.id = id;
            MyApp.token = token;
            if (!view.hasFragment()) addFragment(new ConversationsFragment());
            isLoggedIn = true;
        }
    }

    @Override
    public void onLoginResult(VKAccessToken res) {
        model.savePreferences(res.accessToken, "token");
        model.savePreferences(res.userId, "id");
        MyApp.id = res.userId;
        MyApp.token = res.accessToken;
        isLoggedIn = true;
        if (!view.hasFragment()) addFragment(new ConversationsFragment());
    }

    @Override
    public void onLoginError(VKError error) {

    }

    @Override
    public void detachView() {
        super.detachView();
        model.onDetach();
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

