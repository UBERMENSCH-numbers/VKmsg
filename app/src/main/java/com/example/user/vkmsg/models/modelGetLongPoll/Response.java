
package com.example.user.vkmsg.models.modelGetLongPoll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("ts")
    @Expose
    private Integer ts;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

}
