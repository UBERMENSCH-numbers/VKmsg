package com.example.user.vkmsg.utils

import com.example.user.vkmsg.models.RecyclerItem

fun ArrayList<RecyclerItem>.find(item: RecyclerItem) = this.indexOf(this.find{it.chatId == item.chatId})