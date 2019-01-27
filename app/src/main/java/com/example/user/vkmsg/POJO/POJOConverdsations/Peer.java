
package com.example.user.vkmsg.POJO.POJOConverdsations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Peer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("local_id")
    @Expose
    private Integer localId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

}
