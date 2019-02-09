package com.example.user.vkmsg.utils;

import android.util.Log;

import com.example.user.vkmsg.models.modelGetConversation.Item;
import com.example.user.vkmsg.models.modelGetConversation.Profile;

import java.util.List;

public class SpecialModelMessages {
    public final Item item;
    public final Profile profile;

    private SpecialModelMessages(Item item, List<Profile> profiles) {
        this.item = item;
        this.profile = findProfile(item.getFromId(), profiles);
    }

    private Profile findProfile(int id, List<Profile> profiles) {
        for (Profile profile : profiles) {
            if (profile.getId() == id) return profile;
        }
        Log.e("ERROR", "PROFILE NOT FOUND");
        return null;
    }

    public static SpecialModelMessages create (Item item, List<Profile> profiles) {
        return new SpecialModelMessages(item, profiles);
    }
}
