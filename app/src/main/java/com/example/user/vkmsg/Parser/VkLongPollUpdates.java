package com.example.user.vkmsg.Parser;

import com.example.user.vkmsg.POJO.POJOLongPollHistory.Messages;

import java.util.List;

public class VkLongPollUpdates {
    public int ts;
    public List<NewMessageAdd> newMessageAddList;
}
