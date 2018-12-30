package com.example.user.vkmsg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.vkmsg.POJO.Container;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private String[] data = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private String token;
    private String id;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        login();
    }


    private void initAppBar () {
        MyApp.getVKapi().getUser(id , token , "photo_200","5.92").enqueue(new Callback<Container>() {
            @Override
            public void onResponse(@NonNull Call<Container> call, @NonNull Response<Container> response) {
                if (response.isSuccessful()) {

                    com.example.user.vkmsg.POJO.Response responsePojo = response.body().getResponse().get(0);
                    ((TextView) findViewById(R.id.text_user_name)).setText(responsePojo.getFirstName() + " " + responsePojo.getLastName());
                    Picasso.get()
                            .load(responsePojo.getPhoto200())
                            .placeholder(R.drawable.holder)
                            .error(R.drawable.error)
                            .into((ImageView) findViewById(R.id.image_user));

                } else {
                    Toast.makeText(getApplicationContext(), "response.isSuccessful() == false " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Container> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "failed request", Toast.LENGTH_SHORT).show();
            }
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
}
