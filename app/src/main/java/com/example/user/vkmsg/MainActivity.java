package com.example.user.vkmsg;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.factories.MainActivityPresenterFactory;
import com.example.user.vkmsg.mvp.contracts.MainActivityContract;
import com.example.user.vkmsg.mvp.model.MainActivityModel;
import com.example.user.vkmsg.mvp.presenter.MainActivityPresenter;

import com.example.user.vkmsg.base.BaseActivity;
import com.example.user.vkmsg.base.BaseNavigationFragment;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.Objects;

public class MainActivity extends BaseActivity<MainActivityPresenter, MainActivityContract.View> implements MainActivityContract.View {
    private MainActivityPresenter presenter;

    @NonNull
    @Override
    protected String tag() {
        return "activity";
    }

    @NonNull
    @Override
    protected IPresenterFactory<MainActivityPresenter> getPresenterFactory() {
        return new MainActivityPresenterFactory<>(new MainActivityModel(this));
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull MainActivityPresenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
        this.presenter.viewIsReady();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

                presenter.onLoginResult(res);
//                startService(new Intent(MainActivity.this, LongPollService.class));
            }
            @Override
            public void onError(VKError error) {
                presenter.onLoginError(error);
            }
        }))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setFragment(BaseNavigationFragment fragment) {
        Log.v("Set Fragment" , fragment.getClass().getSimpleName());
        fragment.attachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

    public Boolean hasFragment () {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        return currentFragment != null;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void VkSdkLogin() {
        VKSdk.login(this, VKScope.FRIENDS,VKScope.MESSAGES);
    }

}
