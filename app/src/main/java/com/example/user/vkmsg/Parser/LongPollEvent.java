package com.example.user.vkmsg.Parser;

public class LongPollEvent {

    public static final int ACTION_NewMessageAdd = 4;

    public final int action;

    public int getAction() {
        return action;
    }

    public LongPollEvent(int action) {
        this.action = action;
    }
}
