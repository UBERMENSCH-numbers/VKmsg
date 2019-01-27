
package com.example.user.vkmsg.POJO.POJOGetLongPollReq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Container_____ {
        @SerializedName("ts")
        @Expose
        private Integer ts;
        @SerializedName("updates")
        @Expose
        private List<List<String>> updates = null;

        public Integer getTs() {
            return ts;
        }

        public void setTs(Integer ts) {
            this.ts = ts;
        }

        public List<List<String>> getUpdates() {
            return updates;
        }

        public void setUpdates(List<List<String>> updates) {
            this.updates = updates;
        }
    
}