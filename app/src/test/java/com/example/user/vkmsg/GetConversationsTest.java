package com.example.user.vkmsg;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.transition.Scene;
import android.util.AndroidException;
import android.util.Log;

import com.example.user.vkmsg.POJO.RecyclerItem;
import com.example.user.vkmsg.mvp.model.ConversationRecyclerModel;
import com.example.user.vkmsg.network.Network;
import com.example.user.vkmsg.utils.PhotoOperations;
import com.example.user.vkmsg.utils.SpecialModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetConversationsTest {
    int i = 0;
    RxBus rxBus = new RxBus();

    public Observable<RecyclerItem> getConversation() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("photo_100");
        return Network.getvKapi().getConversations("cea56f5f892b5c90482831fa16749f3bc2809023c02b85d719c332669e58ab32a0da313d107ea9d984f0c", "1", "all", fields, "5.92")
                .observeOn(Schedulers.io())
                .flatMap(container_ -> Observable.just(container_.getResponse()))
                .flatMap(response -> Observable.zip(Observable.fromIterable(response.getItems()),
                        Observable.fromIterable(response.getProfiles()), (a, b) -> SpecialModel.create(a, response.getProfiles()))
                        .map(this::parseData));
    }

    @Test
    public void loadConversations () {
                getConversation()
                        .subscribe(recyclerItem -> loadPic(recyclerItem.getPhotoUrl(), getTarger(recyclerItem)));
    }


    private void loadPic (String url, Target target) {
        Lger.e("tag", "log");
        Picasso.get().load(url).into(target);
    }


    private Target getTarger (RecyclerItem recyclerItem) {
        return new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                recyclerItem.setConversationPic(PhotoOperations.getCroppedBitmap(bitmap));
                rxBus.send(recyclerItem);
                i ++;
                Log.v("TAG", String.valueOf(i));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        };
    }


    public RecyclerItem parseData (SpecialModel itemProfilePair) {
        RecyclerItem recyclerItem = new RecyclerItem();
        recyclerItem.setLastMsg(itemProfilePair.item.getLastMessage().getText());
        recyclerItem.setChatId(itemProfilePair.item.getConversation().getPeer().getId());

        if (itemProfilePair.item.getConversation().getPeer().getType().equals("user")) {
            recyclerItem.setConversationTitle(itemProfilePair.profile.getFirstName() + " " +itemProfilePair.profile.getLastName());
            recyclerItem.setPhotoUrl(itemProfilePair.profile.getPhoto100());
        } else {
            recyclerItem.setConversationTitle(itemProfilePair.item.getConversation().getChatSettings().getTitle());
            recyclerItem.setPhotoUrl(itemProfilePair.item.getConversation().getChatSettings().getPhoto().getPhoto100());
        }
        return recyclerItem;
    }


}

class Lger {
    public static int d(String tag, String msg) {
        System.out.println("DEBUG: " + tag + ": " + msg);
        return 0;
    }

    public static int i(String tag, String msg) {
        System.out.println("INFO: " + tag + ": " + msg);
        return 0;
    }

    public static int w(String tag, String msg) {
        System.out.println("WARN: " + tag + ": " + msg);
        return 0;
    }

    public static int e(String tag, String msg) {
        System.out.println("ERROR: " + tag + ": " + msg);
        return 0;
    }

    // add other methods if required...
}
