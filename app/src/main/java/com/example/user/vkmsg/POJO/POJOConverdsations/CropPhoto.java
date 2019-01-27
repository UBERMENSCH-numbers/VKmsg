
package com.example.user.vkmsg.POJO.POJOConverdsations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropPhoto {

    @SerializedName("photo")
    @Expose
    private Photo____ photo;
    @SerializedName("crop")
    @Expose
    private Crop crop;
    @SerializedName("rect")
    @Expose
    private Rect rect;

    public Photo____ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo____ photo) {
        this.photo = photo;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

}
