
package com.example.user.vkmsg.POJO.POJOLongPollHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conversation {

    @SerializedName("peer")
    @Expose
    private Peer peer;
    @SerializedName("in_read")
    @Expose
    private Integer inRead;
    @SerializedName("out_read")
    @Expose
    private Integer outRead;
    @SerializedName("last_message_id")
    @Expose
    private Integer lastMessageId;
    @SerializedName("can_write")
    @Expose
    private CanWrite canWrite;
    @SerializedName("chat_settings")
    @Expose
    private ChatSettings chatSettings;
    @SerializedName("push_settings")
    @Expose
    private PushSettings pushSettings;

    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public Integer getInRead() {
        return inRead;
    }

    public void setInRead(Integer inRead) {
        this.inRead = inRead;
    }

    public Integer getOutRead() {
        return outRead;
    }

    public void setOutRead(Integer outRead) {
        this.outRead = outRead;
    }

    public Integer getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Integer lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public CanWrite getCanWrite() {
        return canWrite;
    }

    public void setCanWrite(CanWrite canWrite) {
        this.canWrite = canWrite;
    }

    public ChatSettings getChatSettings() {
        return chatSettings;
    }

    public void setChatSettings(ChatSettings chatSettings) {
        this.chatSettings = chatSettings;
    }

    public PushSettings getPushSettings() {
        return pushSettings;
    }

    public void setPushSettings(PushSettings pushSettings) {
        this.pushSettings = pushSettings;
    }

}
