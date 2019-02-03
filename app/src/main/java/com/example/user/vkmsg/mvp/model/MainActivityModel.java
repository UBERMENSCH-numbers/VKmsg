package com.example.user.vkmsg.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.user.vkmsg.mvp.contracts.MainActivityContract;

import io.reactivex.annotations.NonNull;

import static android.content.Context.MODE_PRIVATE;

public class MainActivityModel implements MainActivityContract.Model {
    private SharedPreferences sPref;
    private Context context;

    public MainActivityModel (Context context) {
        this.context = context;
    }

    @Override
    public void savePreferences(@NonNull String value, @NonNull String key) {
        sPref = context.getSharedPreferences(LOGIN_SHARED_PREFERECES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Log.e("SHARED PREFERENCES SAVE", "key " + key + " value " + value);
        ed.putString(key , value);
        ed.apply();
    }

    @Override
    public String loadPreferences(String key) {
        sPref = context.getSharedPreferences(LOGIN_SHARED_PREFERECES ,MODE_PRIVATE);
        String value = sPref.getString(key, "");
        Log.e("SHARED PREFERENCES LOAD", key + " returns " + value + " value");
        return value;
    }
}
