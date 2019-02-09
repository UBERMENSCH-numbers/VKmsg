package com.example.user.vkmsg.mvp.contracts;

import android.graphics.Bitmap;

import com.example.user.vkmsg.ChatFragmentAdapter;
import com.example.user.vkmsg.RxBus;

import io.reactivex.Observable;

public interface ChatRecyclerAdapterContract {
    interface View {
        void setAvatar (Bitmap bitmap);
        void setMessageText (String text);
        void setName (String text);
        void setFromId (int id);
    }
    interface Presenter {
        void attachAdapter(ChatFragmentAdapter adapter);
        void onBindRepositoryRowViewAtPosition(int position, ChatRecyclerAdapterContract.View rowView);
        int getRepositoriesRowsCount();
        void detach ();
        int getLayoutType(int position);
    }
    interface Model {
        void loadData(int id);
        Observable<Bitmap> getPic (String url);
    }
}
