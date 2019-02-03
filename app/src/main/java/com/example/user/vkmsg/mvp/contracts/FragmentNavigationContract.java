package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.mvp.view.BaseFragment;

public interface FragmentNavigationContract {
    interface View {
        void atachPresenter(Presenter presenter);

    }

    interface Presenter {
        void addFragment(BaseFragment fragment);
    }
}