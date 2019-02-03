package com.example.user.vkmsg;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.example.user.vkmsg.mvp.RxBus;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.mvp.model.ConversationRecyclerModel;
import com.example.user.vkmsg.mvp.presenter.ConversationFragmentPresenter;
import com.example.user.vkmsg.mvp.presenter.ConversationRecyclerPresenter;
import com.example.user.vkmsg.mvp.view.BaseFragment;

import java.util.ArrayList;

import static com.vk.sdk.VKUIHelper.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationsFragment extends BaseFragment implements ConversationFragmentContract.View {
    public ConversationRecyclerAdapter conversationRecyclerAdapter;
    private ConversationFragmentContract.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        setRetainInstance(true);
        super.onAttach(context);
        presenter = new ConversationFragmentPresenter(((MyApp) getActivity().getApplication()).bus());
        presenter.attachView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewIsReady();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversations, container, false);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_conversations;
    }

    public void initRecyclerView () {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_dialogs);
        RxBus rxBus = ((MyApp) getActivity().getApplication()).bus();
        ConversationRecyclerPresenter presenter = new ConversationRecyclerPresenter(new ConversationRecyclerModel(rxBus), rxBus);
        conversationRecyclerAdapter = new ConversationRecyclerAdapter(presenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(conversationRecyclerAdapter);
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void setAppBar(AppBar appBar) {
        ((TextView) getView().findViewById(R.id.text_user_name)).setText(appBar.getUserName());
        ((ImageView) getView().findViewById(R.id.image_user)).setImageBitmap(appBar.getPicture());
    }
}
