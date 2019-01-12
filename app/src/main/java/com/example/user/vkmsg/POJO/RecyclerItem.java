package com.example.user.vkmsg.POJO;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.view.DragAndDropPermissions;
import android.widget.ImageView;

public class RecyclerItem {
   private String conversationTitle;
   private String lastMsg;
   private ImageView conversatiomPic;
   private String url;

    public RecyclerItem () {
        conversatiomPic = null;
        lastMsg = null;
        conversationTitle = null;
    }

    public RecyclerItem (String conversationTitle, String lastMsg, ImageView conversationPic) {
        this.conversatiomPic = conversationPic;
        this.lastMsg = lastMsg;
        this.conversationTitle = conversationTitle;
    }

    public void setConversatiomPic(ImageView conversatiomPic) {
        this.conversatiomPic = conversatiomPic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public ImageView getConversatiomPic() {
        return conversatiomPic;
    }

    public void setConversationPic(Drawable conversatiomPic) {
        this.conversatiomPic.setImageDrawable(conversatiomPic);
    }

}
