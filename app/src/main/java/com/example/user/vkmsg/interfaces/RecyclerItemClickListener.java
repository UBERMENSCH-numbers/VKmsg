package com.example.user.vkmsg.interfaces;

import android.view.View;

public interface RecyclerItemClickListener {
    void onClick (int position, boolean isLongClick, int chatId);
}
