package com.example.user.vkmsg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.POJO.MessageModel;
import com.example.user.vkmsg.POJO.POJOLongPollHistory.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private ArrayList<MessageModel> mDataset;
    final int USER_MESSAGE_TYPE = 1;
    final int NOT_USER_MESSAGE_TYPE = 2;



    public ChatAdapter (ArrayList<MessageModel> messageModels) {
        mDataset = messageModels;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        Log.e("TAG", i + " " + String.valueOf(mDataset.get(i).getFrom_id() + "  " + MyApp.id));

        if (i == USER_MESSAGE_TYPE) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_msg_right, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_msg_left, viewGroup, false);
        }

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.from_id = mDataset.get(i).getFrom_id();
        chatViewHolder.text.setText(mDataset.get(i).getText());
        Log.e("Holder",i + " " + String.valueOf(mDataset.get(i).getFrom_id()));

        Picasso.get().
                load(mDataset.get(i).getPhoto_100())

                .into(chatViewHolder.photo);

    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getFrom_id() == Integer.valueOf(MyApp.id)) return USER_MESSAGE_TYPE;
        return NOT_USER_MESSAGE_TYPE;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        int from_id;
        ImageView photo;
        TextView text;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.user_img);
            text = itemView.findViewById(R.id.user_msg);

        }
    }
}
