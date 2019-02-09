package com.example.user.vkmsg.utils;

import android.util.Log;

import com.example.user.vkmsg.POJO.POJOConverdsations.Item;
import com.example.user.vkmsg.POJO.POJOConverdsations.Profile;

import java.util.List;

public class SpecialModelConversation {
    public final Item item;
    public final Profile profile;

    private SpecialModelConversation(Item item, List<Profile> pList) {
        if (item.getConversation().getPeer().getType().equals("user")) {
            this.item = item;
            this.profile = getProfile(item.getConversation().getPeer().getId(), pList);
        } else {
            this.item = item;
            this.profile = null;
        }
    }

    public static SpecialModelConversation create (Item item, List<Profile> pList) {
        return new SpecialModelConversation(item, pList);
    }

    private Profile getProfile (int id, List<Profile> profiles) {
        for (Profile profile:profiles) {
            if (profile.getId() == id) return profile;
        }
        Log.e("ERROR", "NO PROFILE FOUND FOR ID " + id);
        return null;
    }
}
