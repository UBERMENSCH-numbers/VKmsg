
package com.example.user.vkmsg.POJO.POJOLongPollHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo_ photo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo_ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

}
