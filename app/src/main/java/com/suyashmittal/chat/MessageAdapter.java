package com.suyashmittal.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<FriendlyMessage> friendlyMessages;

    public MessageAdapter(List<FriendlyMessage> friendlyMessages) {
        this.friendlyMessages = friendlyMessages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        MessageViewHolder vh = new MessageViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        FriendlyMessage message = friendlyMessages.get(position);
        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            holder.messageTextView.setVisibility(View.GONE);
            holder.photoImageView.setVisibility(View.VISIBLE);
            Glide.with(holder.photoImageView.getContext()).load(message.getPhotoUrl()).into(holder.photoImageView);
        } else {
            holder.messageTextView.setVisibility(View.VISIBLE);
            holder.photoImageView.setVisibility(View.GONE);
            holder.messageTextView.setText(message.getText());
        }
        holder.nameTextView.setText(message.getName());
    }

    @Override
    public int getItemCount() {
        return friendlyMessages.size();
    }

    public void add(FriendlyMessage message){
        friendlyMessages.add(message);
        notifyDataSetChanged();
    }

    public void clear(){
        friendlyMessages.clear();
        notifyDataSetChanged();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{

        public ImageView photoImageView;
        public TextView nameTextView;
        public TextView messageTextView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
