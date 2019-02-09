
package com.example.user.vkmsg.models.modelConversations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("photo")
    @Expose
    private Photo___ photo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Photo___ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo___ photo) {
        this.photo = photo;
    }

}
