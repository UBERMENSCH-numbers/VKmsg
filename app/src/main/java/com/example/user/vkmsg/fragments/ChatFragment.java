package com.example.user.vkmsg.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.base.BaseFragment;
import com.example.user.vkmsg.factories.ChatFragmentConversationFactory;
import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.mvp.contracts.ChatFragmentContract;
import com.example.user.vkmsg.R;
import com.example.user.vkmsg.mvp.model.ChatRecyclerAdapterModel;
import com.example.user.vkmsg.mvp.presenter.ChatFragmentPresenter;
import com.example.user.vkmsg.mvp.presenter.ChatFragmentRecyclerPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment<ChatFragmentContract.Presenter,
        ChatFragmentContract.View> implements ChatFragmentContract.View {
    private int chatId;
    private ChatFragmentAdapter adapter;
    private ChatFragmentContract.Presenter presenter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversation, container, false);
        if (savedInstanceState != null) {
            chatId = savedInstanceState.getInt("chatId");
        }
        if (getArguments() != null) chatId = getArguments().getInt("chatId");
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.conversation;
    }

    @NonNull
    @Override
    protected IPresenterFactory getPresenterFactory() {
        return new ChatFragmentConversationFactory<ChatFragmentPresenter>();
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull ChatFragmentContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
        this.presenter.viewIsReady();
    }


    @Override
    public void initRecycler() {
        RxBus bus = ((MyApp) getActivity().getApplication()).bus();
        RecyclerView recyclerView = getView().findViewById(R.id.messages_recycler);
        ChatFragmentRecyclerPresenter presenter = new ChatFragmentRecyclerPresenter(bus, new ChatRecyclerAdapterModel(bus), chatId);
        ChatFragmentAdapter adapter = new ChatFragmentAdapter(presenter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.onDetach();
    }
}
