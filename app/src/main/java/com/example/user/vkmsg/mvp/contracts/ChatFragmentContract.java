package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.interfaces.IView;

public interface ChatFragmentContract {
    interface View extends IView {
        void initRecycler ();
    }

    interface Presenter extends IPresenter<ChatFragmentContract.View> {
        void attachAdapter (ChatFragmentAdapter adapter);
    }
}
