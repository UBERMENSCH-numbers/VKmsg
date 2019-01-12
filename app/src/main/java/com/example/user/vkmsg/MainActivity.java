package com.example.user.vkmsg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.vkmsg.POJO.POJOConverdsations.Item;
import com.example.user.vkmsg.POJO.POJOUsers.Container;
import com.example.user.vkmsg.POJO.RecyclerItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.util.stream.Collectors.toList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<RecyclerItem> data = new ArrayList<>();
    private String token;
    private String id;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();


    }


    @SuppressLint("CheckResult")
    private void initAppBar () {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        getUsers(ids)
                .map((container) -> container.getResponse().get(0))
                .subscribe(response -> {
                    ((TextView) findViewById(R.id.text_user_name)).setText(response.getFirstName() +
                            " " + response.getLastName());
                    loadPic(response.getPhoto200(), findViewById(R.id.image_user));
                });
        initRecyclerView();

    }

    private void initRecyclerView () {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_dialogs);
        myAdapter = new MyAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        initConversations();
    }



    private void login () {
        VKSdk.login(this, VKScope.FRIENDS,VKScope.MESSAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                id = res.userId;
                token = res.accessToken;
                Toast.makeText(getApplicationContext(), "Auth Success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Auth Failed", Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        initAppBar();
    }

    io.reactivex.Observable<Item> getConversations () {
        return MyApp.getVKapi().getConversations(token,"all", "5.92")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(container_ -> io.reactivex.Observable.fromIterable(container_.getResponse().getItems()));

    }

    @SuppressLint("CheckResult")
    private void initConversations () {
        getConversations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .groupBy(item -> item.getConversation().getPeer().getType().equals("user"))
                .flatMap(group -> {

                    if (group.getKey()) {
                        io.reactivex.Observable<Item> itemObservable = group.cache();
                        io.reactivex.Observable<String> msgObservable = itemObservable.map(item -> item.getLastMessage().getText());

                        io.reactivex.Observable<RecyclerItem> observable = itemObservable
                                .observeOn(Schedulers.io())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .map(group_ -> group_.getConversation().getPeer().getId().toString())
                                .buffer(10)
                                .flatMap(this::getUsers)
                                .flatMap(container -> io.reactivex.Observable.fromIterable(container.getResponse()))
                                .map(response -> {
                                    RecyclerItem recyclerItem = new RecyclerItem();
                                    recyclerItem.setConversationTitle(response.getFirstName() + " " + response.getLastName());
                                    recyclerItem.setUrl(response.getPhoto200());
                                    return recyclerItem;
                                });
                        return io.reactivex.Observable.zip(observable, msgObservable, (recyclerItem, s) -> {
                            recyclerItem.setLastMsg(s);
                            return recyclerItem;
                        });

                    } else {
                        return group.map(group_ -> {
                            RecyclerItem recyclerItem = new RecyclerItem();
                            recyclerItem.setUrl(group_.getConversation().getChatSettings().getPhoto().getPhoto200());
                            recyclerItem.setConversationTitle(group_.getConversation().getChatSettings().getTitle());
                            recyclerItem.setLastMsg(group_.getLastMessage().getText());
                            return recyclerItem;
                        });
                    }
                })
                .doOnNext(item -> Log.e("ITEM EMMITED", item.getConversationTitle()))
//                .doOnNext(item -> Log.e("PIC", ite))
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

    Drawable loadPic (String url) {
        ImageView imageView = new ImageView(getApplicationContext());
        Log.e("PICTURE LOADING", url);
        Picasso.get()
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.holder)
                .into(imageView);
        return imageView.getDrawable();
    }

}
