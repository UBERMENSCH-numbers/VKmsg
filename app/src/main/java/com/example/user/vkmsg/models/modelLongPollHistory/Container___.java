
package com.example.user.vkmsg.models.modelLongPollHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Container___ {

    @SerializedName("response")
    @Expose
    private Response response;

    @SerializedName("error")
    @Expose
    private Error error;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
