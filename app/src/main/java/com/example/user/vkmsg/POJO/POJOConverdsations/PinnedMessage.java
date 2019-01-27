
package com.example.user.vkmsg.POJO.POJOConverdsations;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinnedMessage {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("peer_id")
    @Expose
    private Integer peerId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("conversation_message_id")
    @Expose
    private Integer conversationMessageId;
    @SerializedName("fwd_messages")
    @Expose
    private List<Object> fwdMessages = null;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getConversationMessageId() {
        return conversationMessageId;
    }

    public void setConversationMessageId(Integer conversationMessageId) {
        this.conversationMessageId = conversationMessageId;
    }

    public List<Object> getFwdMessages() {
        return fwdMessages;
    }

    public void setFwdMessages(List<Object> fwdMessages) {
        this.fwdMessages = fwdMessages;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

}
