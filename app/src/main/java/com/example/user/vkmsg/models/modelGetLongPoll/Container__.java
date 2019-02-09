
package com.example.user.vkmsg.models.modelGetLongPoll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Container__ {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
