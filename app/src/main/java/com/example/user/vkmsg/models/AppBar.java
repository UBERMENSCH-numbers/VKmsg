package com.example.user.vkmsg.models;

import android.graphics.Bitmap;

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
