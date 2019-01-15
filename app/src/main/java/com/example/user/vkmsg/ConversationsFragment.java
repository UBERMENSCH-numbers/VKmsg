package com.example.user.vkmsg;


import android.annotation.SuppressLint;
import android.os.Bundle;
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

import com.example.user.vkmsg.POJO.POJOConverdsations.Item;
import com.example.user.vkmsg.POJO.POJOConverdsations.Profile;
import com.example.user.vkmsg.POJO.POJOConverdsations.Response;
import com.example.user.vkmsg.POJO.POJOUsers.Container;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.vk.sdk.VKUIHelper.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationsFragment extends Fragment {
    private String token;
    private String id;

    private MyAdapter myAdapter;
    private ArrayList<RecyclerItem> data = new ArrayList<>();

    public ConversationsFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            Bundle bundle = this.getArguments();
            id = bundle.getString("id");
            token = bundle.getString("token");
        }
        return inflater.inflate(R.layout.fragment_conversations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAppBar();
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void initAppBar () {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        getUsers(ids)
                .map((container) -> container.getResponse().get(0))
                .subscribe(response -> {
                    ((TextView) getView().findViewById(R.id.text_user_name)).setText(response.getFirstName() +
                            " " + response.getLastName());
                    loadPic(response.getPhoto200(), getView().findViewById(R.id.image_user));
                });
        initRecyclerView();
    }

    private void initRecyclerView () {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_dialogs);
        myAdapter = new MyAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        initConversations();
    }

    io.reactivex.Observable<Response> getConversations () {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("photo_100");

        return MyApp.getVKapi().getConversations(token,"1","all", fields,"5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(response -> Log.e("response",new GsonBuilder().setPrettyPrinting().create().toJson(response)))
                .flatMap(container -> io.reactivex.Observable.just(container.getResponse()));
    }

    @SuppressLint("CheckResult")
    private void initConversations () {
        getConversations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> {
                    List<Profile> profiles = response.getProfiles();
                    Log.e("Log", String.valueOf(profiles.size()));
                    Log.e("response",new GsonBuilder().setPrettyPrinting().create().toJson(response.getProfiles().get(10)));

                    List<Item> items = response.getItems();
                    List<Integer> ids = new ArrayList<>();
                    List<RecyclerItem> recyclerItems = new ArrayList<>();
                    for (Profile profile : profiles)
                        ids.add(profile.getId());

                    for (Item item : items) {
                        RecyclerItem recyclerItem = new RecyclerItem();
                        recyclerItem.setLastMsg(item.getLastMessage().getText());

                        if (item.getConversation().getPeer().getType().equals("user")) {
                            int pos = ids.indexOf(item.getConversation().getPeer().getId());
                            recyclerItem.setConversationTitle(profiles.get(pos).getFirstName()
                                    + " " + profiles.get(pos).getLastName());

                            recyclerItem.setUrl(profiles.get(pos).getPhoto100());
                        } else {
                            recyclerItem.setConversationTitle(item.getConversation().getChatSettings().getTitle());
                            recyclerItem.setUrl(item.getConversation().getChatSettings().getPhoto().getPhoto100());
                        }
                        recyclerItems.add(recyclerItem);
                    }
                    return Observable.fromIterable(recyclerItems);
                })
                .doOnNext(item -> Log.e("ITEM EMMITED", item.getConversationTitle()))
                .subscribe(item -> data.add(item),
                        Throwable::printStackTrace,
                        () -> myAdapter.notifyDataSetChanged());
    }

    io.reactivex.Observable<Container> getUsers (List<String> ids) {
        StringBuilder stringBuffer = new StringBuilder();
        for (String str : ids){
            stringBuffer.append(str);
            if (ids.indexOf(str) != ids.size()) stringBuffer.append(",");
        }

        return MyApp.getVKapi().getUser(stringBuffer.toString(),  token, "photo_200", "5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    void loadPic (String url, ImageView place) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.holder)
                .error(R.drawable.error)
                .into(place);
    }

}
