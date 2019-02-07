package com.example.user.vkmsg.parser;

public class NewMessageAdd {
    public int messageId;
    public int peerId;
    public int time;
    public String text;

    public NewMessageAdd (int messageId, int peerId, int time, String text) {
       this.messageId = messageId;
       this.peerId = peerId;
       this.time = time;
       this.text = text;
    }

    public NewMessageAdd() {

    }
}
