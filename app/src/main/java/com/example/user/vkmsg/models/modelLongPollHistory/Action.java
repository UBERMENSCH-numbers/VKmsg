
package com.example.user.vkmsg.models.modelLongPollHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
