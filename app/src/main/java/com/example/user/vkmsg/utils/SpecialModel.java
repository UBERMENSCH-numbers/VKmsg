package com.example.user.vkmsg.utils;

import android.util.Log;

import com.example.user.vkmsg.POJO.POJOConverdsations.Item;
import com.example.user.vkmsg.POJO.POJOConverdsations.Profile;
import com.example.user.vkmsg.mvp.model.ConversationRecyclerModel;

import java.util.List;

public class SpecialModel {
    public final Item item;
    public final Profile profile;

    SpecialModel(Item item, List<Profile> pList) {
        if (item.getConversation().getPeer().getType().equals("user")) {
            this.item = item;
            this.profile = getProfile(item.getConversation().getPeer().getId(), pList);
        } else {
            this.item = item;
            this.profile = null;
        }
    }

    public static SpecialModel create (Item item, List<Profile> pList) {
        return new SpecialModel(item, pList);
    }

    private Profile getProfile (int id, List<Profile> profiles) {
        for (Profile profile:profiles) {
            if (profile.getId() == id) return profile;
        }
        Log.e("ERROR", "NO PROFILE FOUND FOR ID " + id);
        return null;
    }
}
