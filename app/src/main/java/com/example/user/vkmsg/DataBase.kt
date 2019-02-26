package com.example.user.vkmsg

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.user.vkmsg.models.RecyclerItem

class DataBase (context : Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    fun getAllConversations(): List<RecyclerItem> {
        var conversations : ArrayList<RecyclerItem> = arrayListOf()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $DB_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var item = RecyclerItem()
                    item.chatId = cursor.getString(cursor.getColumnIndex(COLUMN_CONVERSATION_ID)).toInt()
                    item.conversationTitle = cursor.getString(cursor.getColumnIndex(COLUMN_CONVERSATION_TITLE))
                    item.lastMsg = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_MESSAGE))
                    item.photoUrl = cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO_URL))
                    item.unreadCount = cursor.getInt(cursor.getColumnIndex(COLUMN_UNREAD_COUNT))
                    conversations.add(item)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        Log.d("Database Process", "Read ${conversations.size} elements")
        return conversations
    }

    fun clearConversations () {
        val db = this.writableDatabase
        db.delete(DB_NAME, null, null)
        db.close()
        Log.d("Database Process", "Base $DB_NAME cleared")

    }

    fun addUser(item: RecyclerItem): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_CONVERSATION_TITLE, item.conversationTitle)
        values.put(COLUMN_LAST_MESSAGE, item.lastMsg)
        values.put(COLUMN_CONVERSATION_ID, item.chatId)
        values.put(COLUMN_PHOTO_URL, item.photoUrl)
        values.put(COLUMN_UNREAD_COUNT, item.unreadCount)
        val _success = db.insert(DB_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_CONVERSATIONS_TABLE = ("CREATE TABLE " + DB_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CONVERSATION_TITLE + " TEXT, "
                + COLUMN_LAST_MESSAGE + " TEXT, "
                + COLUMN_PHOTO_URL + " TEXT, "
                + COLUMN_CONVERSATION_ID + " TEXT, "
                + COLUMN_UNREAD_COUNT + " INTEGER" + ")")
        db.execSQL(SQL_CREATE_CONVERSATIONS_TABLE)
    }

    companion object : BaseColumns {
        private const val DB_NAME = "ConversationsBase"
        private const val DB_VERSION = 1
        private const val _ID = BaseColumns._ID
        private const val COLUMN_CONVERSATION_TITLE = "Title"
        private const val COLUMN_LAST_MESSAGE = "LastMessage"
        private const val COLUMN_CONVERSATION_ID = "ConversationId"
        private const val COLUMN_PHOTO_URL = "PhotoUrl"
        private const val COLUMN_UNREAD_COUNT = "UnreadCount"
    }
}
