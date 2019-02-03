package com.example.user.vkmsg;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.example.user.vkmsg.mvp.contracts.MainActivityContract;
import com.example.user.vkmsg.mvp.interfaces.IPresenter;
import com.example.user.vkmsg.mvp.model.MainActivityModel;
import com.example.user.vkmsg.mvp.presenter.MainActivityPresenter;

import com.example.user.vkmsg.mvp.presenter.PresenterFactory;
import com.example.user.vkmsg.mvp.view.BaseFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, LoaderManager.LoaderCallbacks<IPresenter> {
    private MainActivityPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(1, null, this);

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
    public void setFragment(BaseFragment fragment) {
        fragment.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

    @Override
    public void onClick(int position, boolean isLongClick, int id) { }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void VkSdkLogin() {
        VKSdk.login(this, VKScope.FRIENDS,VKScope.MESSAGES);
    }

    @NonNull
    @Override
    public Loader<IPresenter> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new PresenterLoader<>(this, () -> new MainActivityPresenter(new MainActivityModel(this)));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<IPresenter> loader, IPresenter iPresenter) {
        this.presenter = (MainActivityPresenter) iPresenter;
        presenter.attachView(this);
        presenter.login();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<IPresenter> loader) {
        presenter = null;
    }
}
