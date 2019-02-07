package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.base.BaseNavigationFragment;

public interface FragmentNavigationContract {
    interface View {
        void attachPresenter(Presenter presenter);

    }

    interface Presenter {
        void addFragment(BaseNavigationFragment fragment);
    }
}