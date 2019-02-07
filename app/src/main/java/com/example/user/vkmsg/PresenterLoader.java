package com.example.user.vkmsg;


import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.interfaces.IPresenter;

public final class PresenterLoader<T extends IPresenter> extends Loader<T> {

    private final IPresenterFactory<T> factory;
    private final String tag;
    private T presenter;

    public PresenterLoader(Context context, IPresenterFactory factory, String tag) {
        super(context);
        this.factory = factory;
        this.tag = tag;
    }

    @Override
    protected void onStartLoading() {
        Log.i("loader", "onStartLoading-" + tag);

        // if we already own a presenter instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.i("loader", "onForceLoad-" + tag);

        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
        Log.i("loader", "deliverResult-" + tag);
    }

    @Override
    protected void onStopLoading() {
        Log.i("loader", "onStopLoading-" + tag);
    }

    @Override
    protected void onReset() {
        Log.i("loader", "onReset-" + tag);
        if (presenter != null) {
            presenter.destroy();
            presenter = null;
        }
    }

    public T getPresenter() {
        return presenter;
    }
}