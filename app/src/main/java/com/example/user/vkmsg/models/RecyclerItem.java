package com.example.user.vkmsg.models;

public class RecyclerItem {
   private String conversationTitle;
   private String lastMsg;
   private String photoUrl;
   private int chatId;
   private int unreadCount;


    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
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

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

}
