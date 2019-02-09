package com.example.user.vkmsg.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.vkmsg.ConversationRecyclerAdapter;
import com.example.user.vkmsg.MyApp;
import com.example.user.vkmsg.POJO.AppBar;
import com.example.user.vkmsg.R;
import com.example.user.vkmsg.RxBus;
import com.example.user.vkmsg.base.BaseNavigationFragment;
import com.example.user.vkmsg.factories.ConversationFragmentPresenterFactory;
import com.example.user.vkmsg.factories.IPresenterFactory;
import com.example.user.vkmsg.base.BaseFragment;
import com.example.user.vkmsg.interfaces.RecyclerItemClickListener;
import com.example.user.vkmsg.mvp.contracts.ConversationFragmentContract;
import com.example.user.vkmsg.mvp.model.ConversationRecyclerModel;
import com.example.user.vkmsg.mvp.presenter.ConversationFragmentPresenter;
import com.example.user.vkmsg.mvp.presenter.ConversationRecyclerPresenter;

import static com.vk.sdk.VKUIHelper.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationsFragment extends BaseFragment<ConversationFragmentPresenter,
        ConversationFragmentContract.View> implements ConversationFragmentContract.View, RecyclerItemClickListener {
    public ConversationFragmentContract.Presenter presenter;
    public ConversationRecyclerAdapter conversationRecyclerAdapter;

    @NonNull
    @Override
    protected String tag() {
        return "conversationFragment";
    }

    @NonNull
    @Override
    protected IPresenterFactory<ConversationFragmentPresenter> getPresenterFactory() {
        return new ConversationFragmentPresenterFactory<>(((MyApp) getActivity()
                .getApplication()).bus());

    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull ConversationFragmentPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
            presenter.attachView(getPresenterView());
            presenter.viewIsReady();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_conversations;
    }

    public void initRecyclerView () {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_dialogs);
        RxBus rxBus = ((MyApp) getActivity().getApplication()).bus();
        ConversationRecyclerPresenter presenter = new ConversationRecyclerPresenter
                (new ConversationRecyclerModel(rxBus), rxBus);

        conversationRecyclerAdapter = new ConversationRecyclerAdapter(presenter);
        conversationRecyclerAdapter.setRecyclerItemClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(conversationRecyclerAdapter);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        conversationRecyclerAdapter.detach();
        presenter.detachView();
        presenter = null;
        conversationRecyclerAdapter = null;
    }

    @Override
    public void setAppBar(AppBar appBar) {
        if (getView() != null) {
            ((TextView) getView().findViewById(R.id.text_user_name)).setText(appBar.getUserName());
            ((ImageView) getView().findViewById(R.id.image_user)).setImageBitmap(appBar.getPicture());
        } else Log.e("ERROR", "VIEW IS NULL!");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getTag(), "Conversation Fragment Created");
    }

    @Override
    public void onClick(int position, boolean isLongClick, int chatId) {
        showToast(String.valueOf(position));
    }
}
