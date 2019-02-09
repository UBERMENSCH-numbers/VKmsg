
package com.example.user.vkmsg.models.modelGetConversation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment__ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo__ photo;

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

}
