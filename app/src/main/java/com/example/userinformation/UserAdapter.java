package com.example.userinformation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    UserAdapter(Context context, List<User> data, ItemClickListener onClick) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.username.setText(mData.get(position).getUsername());
        holder.userAddress.setText(mData.get(position).getUserAddress());
        holder.userNumber.setText(mData.get(position).getUserNumber());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition(), mData.get(position).id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView username;
        public TextView userNumber;
        public TextView userAddress;
        public ImageView delete;

        ViewHolder(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.userAddress = itemView.findViewById(R.id.userAddress);
            this.userNumber =  itemView.findViewById(R.id.userNumber);
            this.delete = itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    User getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, String id);
    }
}