
package com.example.user.vkmsg.POJO.POJOLongPollHistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sticker {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("sticker_id")
    @Expose
    private Integer stickerId;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("images_with_background")
    @Expose
    private List<ImagesWithBackground> imagesWithBackground = null;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStickerId() {
        return stickerId;
    }

    public void setStickerId(Integer stickerId) {
        this.stickerId = stickerId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ImagesWithBackground> getImagesWithBackground() {
        return imagesWithBackground;
    }

    public void setImagesWithBackground(List<ImagesWithBackground> imagesWithBackground) {
        this.imagesWithBackground = imagesWithBackground;
    }

}
