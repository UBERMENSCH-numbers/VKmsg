package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.interfaces.RecyclerItemClickListener;
import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.interfaces.IView;

public interface ConversationFragmentContract {

    interface View extends IView {
        void setAppBar (AppBar appBar);
        void initRecyclerView();
    }

    interface Presenter extends IPresenter<ConversationFragmentContract.View> {

    }

    interface Model {
        void downloadAppBar (AppBar appBar);
    }
}
