package com.example.user.vkmsg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.nio.file.Path;


public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {
    public static String token;
    private String id;
    private FragmentTransaction fragmentTransaction;
    private ConversationsFragment conversationsFragment;
    private FragmentManager fragmentManager;
    private Fragment lastFragment;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!VKSdk.isLoggedIn() || loadText("token") == null) {
            login();

        } else {
            token = loadText("token");
            id = loadText("id");
            showConversations();
        }
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
            }}))
            super.onActivityResult(requestCode, resultCode, data);
        saveText("token", token);
        saveText("id", id);
        showConversations();
    }

    @Override
    public void onBackPressed() {
        if (lastFragment instanceof ChatFragment) {
            showFragment(conversationsFragment);
        }
    }

    private void showConversations () {
        conversationsFragment = new ConversationsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("token", token);
        conversationsFragment.setArguments(bundle);
        showFragment(conversationsFragment);
        startService(new Intent(MainActivity.this, LongPollService.class));
    }

    private void showFragment (Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (lastFragment != null &&
                lastFragment.equals(conversationsFragment)) {
            fragmentTransaction.hide(conversationsFragment);
        }

        if (fragment instanceof ChatFragment) {
            fragmentTransaction.hide(conversationsFragment);
        }

        if (fragment.isAdded()) {
            fragmentTransaction.remove(lastFragment);
            fragmentTransaction.show(fragment);
            lastFragment = fragment;
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment);
            lastFragment = fragment;
        }
        fragmentTransaction.commit();



    }


    @Override
    public void onClick(View view, int position, boolean isLongClick, int id) {
        Log.e("Tag", "Click");
        if (!isLongClick) {
            ChatFragment chatFragment = new ChatFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            chatFragment.setArguments(bundle);
            showFragment(chatFragment);
        }
    }

    void saveText(String key, String value) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Log.e("SHARED PREF save", "key " + key + " value " + value);
        ed.putString(key , value);
        ed.apply();
    }

    String loadText(String key) {
        sPref = getPreferences(MODE_PRIVATE);
        Log.e("SHARED PREF get",  sPref.getString(key, ""));
        return sPref.getString(key, "");
    }

}




//
//public void showMenuFragment( Fragment fragment, String tag, boolean addToBackStack ) {
//
//        showFragment( R.id.menu_frame, fragment, tag, getLastMenuPushed(), addToBackStack );
//        setLastMenuPushed( tag );
//        }
//
//protected void showFragment( int resId, Fragment fragment, String tag, String lastTag, boolean addToBackStack ) {
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        if ( lastTag != null ) {
//        Fragment lastFragment = fragmentManager.findFragmentByTag( lastTag );
//        if ( lastFragment != null ) {
//        transaction.hide( lastFragment );
//        }
//        }
//
//        if ( fragment.isAdded() ) {
//        transaction.show( fragment );
//        }
//        else {
//        transaction.add( resId, fragment, tag ).setBreadCrumbShortTitle( tag );
//        }
//
//        if ( addToBackStack ) {
//        transaction.addToBackStack( tag );
//        }
//
//        transaction.commit();
//        }