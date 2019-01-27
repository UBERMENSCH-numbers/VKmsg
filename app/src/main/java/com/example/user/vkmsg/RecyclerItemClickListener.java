package com.example.user.vkmsg;

import android.view.View;

public interface RecyclerItemClickListener {
    void onClick (View view, int position, boolean isLongClick, int chatId);
}
