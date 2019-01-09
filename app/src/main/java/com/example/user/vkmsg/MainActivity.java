package com.example.user.vkmsg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.vkmsg.POJO.POJOUsers.Container;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private String[] data = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private String token;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
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
    }

    private void initRecyclerView () {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_dialogs);
        MyAdapter myAdapter = new MyAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
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
