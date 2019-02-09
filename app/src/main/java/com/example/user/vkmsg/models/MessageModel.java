package com.example.user.vkmsg.models;

public class MessageModel implements Comparable<MessageModel> {
    String text;
    String user_name;
    String photo_100;
    int messageId;
    int from_id;

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
