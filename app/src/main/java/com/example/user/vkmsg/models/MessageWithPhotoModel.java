package com.example.user.vkmsg.models;

public class MessageWithPhotoModel extends MessageModel {
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    private String photoUrl;

}
