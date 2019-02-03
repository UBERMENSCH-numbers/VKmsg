package com.example.user.vkmsg.POJO;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.user.vkmsg.POJO.POJOLongPollHistory.Image;

public class AppBar {
    public String userName;
    public Bitmap picture;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

}
