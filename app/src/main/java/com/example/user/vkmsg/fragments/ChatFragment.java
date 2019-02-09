package com.example.user.vkmsg.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkmsg.MainActivity;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.base.BaseFragment;
import com.example.user.vkmsg.factories.ChatFragmentConversationFactory;
import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.mvp.contracts.ChatFragmentContract;
import com.example.user.vkmsg.R;
import com.example.user.vkmsg.mvp.model.ChatRecyclerAdapterModel;
import com.example.user.vkmsg.mvp.presenter.ChatFragmentRecyclerPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment<ChatFragmentContract.Presenter,
        ChatFragmentContract.View> implements ChatFragmentContract.View {
    private int chatId;
    private ChatFragmentContract.Presenter presenter;

    public ChatFragment() {
        chatId = getArguments().getInt("chatId");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    protected String tag() {
        return "chat-fragment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversation, container, false);
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.conversation;
    }

    @NonNull
    @Override
    protected IPresenterFactory getPresenterFactory() {
        return ChatFragmentConversationFactory::new;
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull ChatFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void initRecycler() {
        RxBus bus = ((MyApp) getActivity().getApplication()).bus();
        ChatFragmentRecyclerPresenter presenter = new ChatFragmentRecyclerPresenter(bus, new ChatRecyclerAdapterModel(bus), chatId);

    }

    @Override
    public void showToast(String text) {

    }
}
