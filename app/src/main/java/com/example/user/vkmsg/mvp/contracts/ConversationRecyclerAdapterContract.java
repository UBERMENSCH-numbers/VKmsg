package com.example.user.vkmsg.mvp.contracts;

import android.graphics.Bitmap;

import com.example.user.vkmsg.ConversationRecyclerAdapter;

import java.io.IOException;

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
        void detach ();
    }

    interface Model {
        Observable<Bitmap> getPic(String url) throws IOException;
        void loadConversations ();
    }

}
