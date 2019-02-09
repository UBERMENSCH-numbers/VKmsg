package com.example.user.vkmsg;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.interfaces.RecyclerItemClickListener;
import com.example.user.vkmsg.mvp.contracts.ConversationRecyclerAdapterContract;

public class ConversationRecyclerAdapter extends RecyclerView.Adapter<ConversationRecyclerAdapter.ConversationViewHolder>{
    private RecyclerItemClickListener recyclerItemClickListener;
    private ConversationRecyclerAdapterContract.Presenter presenter;

    public ConversationRecyclerAdapter(ConversationRecyclerAdapterContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.attachAdapter(this);
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        ConversationViewHolder holder = new ConversationViewHolder(v);
        holder.itemView.setOnClickListener((View view) -> {
            recyclerItemClickListener.onClick(holder.getAdapterPosition(), false, holder.id);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position)  {
        presenter.onBindRepositoryRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesRowsCount();
    }

    public void setRecyclerItemClickListener (RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public void detach () {
        presenter.detach();
        presenter = null;
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder implements ConversationRecyclerAdapterContract.View {
        private TextView mTitle;
        private TextView mMsg;
        private ImageView mPic;
        private int id;

        public ConversationViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.personNameTextView);
            mMsg = itemView.findViewById(R.id.lastMsgTextView);
            mPic = itemView.findViewById(R.id.conversationPic);
        }

        @Override
        public void setTitle(String title) {
            mTitle.setText(title);
        }

        @Override
        public void setLastMsg(String lastMsg) {
            mMsg.setText(lastMsg);
        }

        @Override
        public void setImageView(Bitmap bitmap) {
            mPic.setImageBitmap(bitmap);
        }

        @Override
        public void setId(int id) {
            this.id = id;
        }
    }

}
