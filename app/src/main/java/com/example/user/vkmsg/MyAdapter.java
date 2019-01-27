package com.example.user.vkmsg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.POJO.RecyclerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private RecyclerItemClickListener recyclerItemClickListener;
    private ArrayList<RecyclerItem> mDataset;

    MyAdapter(ArrayList<RecyclerItem> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)  {
        holder.mTitle.setText(mDataset.get(position).getConversationTitle());
        holder.mMsg.setText(mDataset.get(position).getLastMsg());
        holder.id = mDataset.get(position).getChatId();

        holder.itemView.setOnClickListener(v -> {
            recyclerItemClickListener.onClick(holder.itemView, holder.getAdapterPosition(), false, holder.id);
        });

        if (holder.mPic.getDrawable() == null)
            Picasso.get()
                    .load(mDataset.get(position).getUrl())
                    .placeholder(R.drawable.holder)
                    .error(R.drawable.error)
                    .into(holder.mPic);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setRecyclerItemClickListener (RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mMsg;
        ImageView mPic;
        int id;

        MyViewHolder(View v) {
            super(v);
            mTitle = v.findViewById(R.id.personNameTextView);
            mMsg = v.findViewById(R.id.lastMsgTextView);
            mPic = v.findViewById(R.id.conversationPic);
        }

    }
}
