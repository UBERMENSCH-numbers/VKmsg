package com.example.user.vkmsg.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkmsg.mvp.contracts.FragmentNavigationContract;

public abstract class BaseFragment extends Fragment implements FragmentNavigationContract.View {

    // the root view
    protected View rootView;
    /**
     * navigation presenter instance
     * declared in base for easier access
     */
    protected FragmentNavigationContract.Presenter navigationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    protected abstract int getLayout();

    /**
     * set the navigation presenter instance
     * @param presenter
     */
    @Override
    public void atachPresenter(FragmentNavigationContract.Presenter presenter) {
        navigationPresenter = presenter;
    }

}