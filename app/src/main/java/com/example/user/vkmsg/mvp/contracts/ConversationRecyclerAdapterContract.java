package com.example.user.vkmsg.mvp.contracts;

import android.graphics.Bitmap;

import com.example.user.vkmsg.ConversationRecyclerAdapter;
import com.example.user.vkmsg.POJO.RecyclerItem;

import io.reactivex.Observable;

public interface ConversationRecyclerAdapterContract {
    interface View {
        void setTitle(String title);
        void setLastMsg(String lastMsg);
        void setImageView (Bitmap bitmap);
        void setId (int id);
        void notifyData();
    }

    interface Presenter {
        void onBindRepositoryRowViewAtPosition(int position, ConversationRecyclerAdapterContract.View rowView);
        int getRepositoriesRowsCount();
        void attachAdapter(ConversationRecyclerAdapter adapter);
    }

    interface Model {
        void loadConversations ();
    }

}
