package com.example.user.vkmsg.models;

import android.graphics.Bitmap;

public class RecyclerItem {
   private String conversationTitle;
   private String lastMsg;
   private Bitmap conversationPic;
   private String photoUrl;
   private int chatId;

    public RecyclerItem () {
        conversationPic = null;
        lastMsg = null;
        conversationTitle = null;
    }

    public RecyclerItem (String conversationTitle, String lastMsg, Bitmap conversationPic) {
        this.conversationPic = conversationPic;
        this.lastMsg = lastMsg;
        this.conversationTitle = conversationTitle;
    }

    public void setConversationPic(Bitmap conversationPic) {
        this.conversationPic = conversationPic;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getConversationTitle() {
        return conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public Bitmap getConversationPic() {
        return conversationPic;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

}
