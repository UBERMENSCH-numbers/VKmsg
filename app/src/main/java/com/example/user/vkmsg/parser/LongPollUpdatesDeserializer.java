package com.example.user.vkmsg.parser;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LongPollUpdatesDeserializer implements JsonDeserializer<VkLongPollUpdates> {
    @Override
    public VkLongPollUpdates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        VkLongPollUpdates updates = new VkLongPollUpdates();
        updates.newMessageAddList = new ArrayList<>();
        JsonObject updatesJson = json.getAsJsonObject();
        updates.ts = updatesJson.get("ts").getAsInt();
        JsonArray updatesArray = updatesJson.getAsJsonArray("updates");
        Log.e("JsonDeserializeTag", String.valueOf(updatesArray.size()));

        for (JsonElement elementArray : updatesArray) {
            JsonArray actionDescription = elementArray.getAsJsonArray();
            if (actionDescription.get(0).getAsInt() == 4) {
                NewMessageAdd message = new NewMessageAdd();
                message.messageId = actionDescription.get(1).getAsInt();
                message.peerId = actionDescription.get(3).getAsInt();
                message.text = actionDescription.get(5).getAsString();
                updates.newMessageAddList.add(message);
            }
        }

        return updates;
    }
}
