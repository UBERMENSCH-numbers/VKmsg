
package com.example.user.vkmsg.POJO.POJOConverdsations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("conversation")
    @Expose
    private Conversation conversation;
    @SerializedName("last_message")
    @Expose
    private LastMessage lastMessage;

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(LastMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

}
