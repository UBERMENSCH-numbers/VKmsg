package com.example.user.vkmsg.POJO;

public class MessageModel implements Comparable<MessageModel> {
    String text;
    int chat_id;
    int from_id;
    String photo_100;

    int messageId;

    public MessageModel(String text, int chat_id, int from_id, String photo_100) {
        this.text = text;
        this.chat_id = chat_id;
        this.from_id = from_id;
        this.photo_100 = photo_100;
    }

    public MessageModel() {
    }

    public String getText() {
        return text;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int peer_id) {
        this.from_id = peer_id;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(String photo_100) {
        this.photo_100 = photo_100;
    }


    @Override
    public int compareTo(MessageModel o) {
        return o.getMessageId();
    }
}
