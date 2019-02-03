package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.RecyclerItemClickListener;
import com.example.user.vkmsg.mvp.interfaces.IPresenter;
import com.example.user.vkmsg.mvp.interfaces.IView;

import io.reactivex.Observable;

public interface ConversationFragmentContract {

    interface View extends IView {
        void setAppBar (AppBar appBar);
        void initRecyclerView();
    }

    interface Presenter extends RecyclerItemClickListener, IPresenter {

    }

    interface Model {
        void downloadAppBar (AppBar appBar);
    }
}
