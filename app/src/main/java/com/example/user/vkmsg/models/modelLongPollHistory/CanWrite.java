
package com.example.user.vkmsg.models.modelLongPollHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CanWrite {

    @SerializedName("allowed")
    @Expose
    private Boolean allowed;

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

}
