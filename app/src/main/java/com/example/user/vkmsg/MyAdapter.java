package com.example.user.vkmsg;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vkmsg.POJO.RecyclerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<RecyclerItem> mDataset;

    public MyAdapter(ArrayList<RecyclerItem> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitle.setText(mDataset.get(position).getConversationTitle());
        holder.mMsg.setText(mDataset.get(position).getLastMsg());
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mMsg;
        public ImageView mPic;

        public MyViewHolder(View v) {
            super(v);
            mTitle = v.findViewById(R.id.personNameTextView);
            mMsg = v.findViewById(R.id.lastMsgTextView);
            mPic = v.findViewById(R.id.conversationPic);
        }
    }
}
