
package com.example.user.vkmsg.POJO;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Container {

    @SerializedName("response")
    @Expose
    private ArrayList<Response> response = null;

    public ArrayList<Response> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Response> response) {
        this.response = response;
    }

}