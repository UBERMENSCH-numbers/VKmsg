package com.example.user.vkmsg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;


public class MainActivity extends AppCompatActivity {
    String[] data = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }


    private void initAppBar () {
        MyApp.getVKapi().getUser()
    }

    private void initRecyclerView () {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_dialogs);
        MyAdapter myAdapter = new MyAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void login () {
        VKSdk.initialize(getApplicationContext());
        VKSdk.login(this, VKScope.FRIENDS, VKScope.EMAIL, VKScope.WALL,
                VKScope.PHOTOS, VKScope.NOHTTPS, VKScope.MESSAGES, VKScope.DOCS,
                VKScope.GROUPS, VKScope.PAGES, VKScope.MESSAGES, VKScope.OFFLINE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
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
    }
}
