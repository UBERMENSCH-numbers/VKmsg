package com.example.user.vkmsg;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.mvp.contracts.ChatRecyclerAdapterContract;


public class ChatFragmentAdapter extends RecyclerView.Adapter<ChatFragmentAdapter.ChatViewHolder> {
    ChatRecyclerAdapterContract.Presenter presenter;
    final int USER_MESSAGE_TYPE = 1;
    final int NOT_USER_MESSAGE_TYPE = 2;

    public ChatFragmentAdapter(ChatRecyclerAdapterContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachAdapter(this);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == USER_MESSAGE_TYPE) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_msg_right, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_msg_left, viewGroup, false);
        }

        return new ChatViewHolder(view, i);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        presenter.onBindRepositoryRowViewAtPosition(i, chatViewHolder);
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getLayoutType(position);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesRowsCount();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements ChatRecyclerAdapterContract.View{
        TextView user_name;
        int from_id;
        ImageView photo;
        TextView text;
        int type;

        public ChatViewHolder(@NonNull View itemView, int type) {
            super(itemView);
            this.type = type;
            text = itemView.findViewById(R.id.message_body);
            if (type == NOT_USER_MESSAGE_TYPE) {
                user_name = itemView.findViewById(R.id.name);
                photo = itemView.findViewById(R.id.avatar);
            }

        }

        @Override
        public void setAvatar(Bitmap bitmap) {
            photo.setImageBitmap(bitmap);
        }

        @Override
        public void setMessageText(String text) {
            this.text.setText(text);
        }

        @Override
        public void setName(String text) {
            this.text.setText(text);
        }

        @Override
        public void setFromId (int id) {
            this.from_id = id;
        }

    }
}
