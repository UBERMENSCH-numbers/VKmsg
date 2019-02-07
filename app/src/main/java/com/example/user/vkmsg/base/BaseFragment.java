package com.example.user.vkmsg.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkmsg.PresenterLoader;
import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.interfaces.IView;
import com.example.user.vkmsg.mvp.contracts.FragmentNavigationContract;

public abstract class BaseFragment<P extends IPresenter<V>, V extends IView> extends com.example.user.vkmsg.base.BaseNavigationFragment {

    private static final String TAG = "base-fragment";
    private static final int LOADER_ID = 101;

    private P presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated-" + tag());

        Loader<P> loader = getLoaderManager().getLoader(loaderId());
        if (loader == null) {
            initLoader();
        } else {
            this.presenter = ((PresenterLoader<P>) loader).getPresenter();
            onPresenterCreatedOrRestored(presenter);
        }
    }

    private void initLoader() {
        // LoaderCallbacks as an object, so no hint regarding loader will be leak to the subclasses.
        getLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                Log.i(TAG, "onCreateLoader-" + tag());
                return new PresenterLoader<>(getContext(), getPresenterFactory(), tag());
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                Log.i(TAG, "onLoadFinished-" + tag());
                BaseFragment.this.presenter = presenter;
                onPresenterCreatedOrRestored(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                Log.i(TAG, "onLoaderReset-" + tag());
                BaseFragment.this.presenter = null;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-" + tag());
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
        Log.i(TAG, "onStop-" + tag());
    }



    /**
     * String tag use for log purposes.
     */
    @NonNull
    protected abstract String tag();

    @Override
    public void attachPresenter(FragmentNavigationContract.Presenter presenter) {
        super.attachPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    /**
     * Instance of {@link IPresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract IPresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses that deliver the {@link IPresenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterCreatedOrRestored(@NonNull P presenter);

    /**
     * Override in case of fragment not implementing Presenter<View> interface
     */
    @NonNull
    protected V getPresenterView() {
        return (V) this;
    }

    /**
     * Use this method in case you want to specify a spefic ID for the {@link PresenterLoader}.
     * By default its value would be {@link #LOADER_ID}.
     */
    protected int loaderId() {
        return LOADER_ID;
    }
}