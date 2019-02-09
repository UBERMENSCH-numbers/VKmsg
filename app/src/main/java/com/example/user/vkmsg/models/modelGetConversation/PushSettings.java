
package com.example.user.vkmsg.models.modelGetConversation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PushSettings {

    @SerializedName("no_sound")
    @Expose
    private Boolean noSound;
    @SerializedName("disabled_until")
    @Expose
    private Integer disabledUntil;
    @SerializedName("disabled_forever")
    @Expose
    private Boolean disabledForever;

    public Boolean getNoSound() {
        return noSound;
    }

    public void setNoSound(Boolean noSound) {
        this.noSound = noSound;
    }

    public Integer getDisabledUntil() {
        return disabledUntil;
    }

    public void setDisabledUntil(Integer disabledUntil) {
        this.disabledUntil = disabledUntil;
    }

    public Boolean getDisabledForever() {
        return disabledForever;
    }

    public void setDisabledForever(Boolean disabledForever) {
        this.disabledForever = disabledForever;
    }

}
