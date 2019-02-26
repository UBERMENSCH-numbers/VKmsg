
package com.example.user.vkmsg.models.modelConversations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo__ photo;
    @SerializedName("call")
    @Expose
    private Call call;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo__ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo__ photo) {
        this.photo = photo;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

}
