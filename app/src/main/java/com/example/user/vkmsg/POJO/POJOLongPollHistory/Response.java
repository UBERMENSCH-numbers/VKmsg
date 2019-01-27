
package com.example.user.vkmsg.POJO.POJOLongPollHistory;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Serializable {

    @SerializedName("history")
    @Expose
    private List<List<Integer>> history = null;
    @SerializedName("messages")
    @Expose
    private Messages messages;
    @SerializedName("new_pts")
    @Expose
    private Integer newPts;
    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = null;
    @SerializedName("conversations")
    @Expose
    private List<Conversation> conversations = null;

    public List<List<Integer>> getHistory() {
        return history;
    }

    public void setHistory(List<List<Integer>> history) {
        this.history = history;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Integer getNewPts() {
        return newPts;
    }

    public void setNewPts(Integer newPts) {
        this.newPts = newPts;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

}
