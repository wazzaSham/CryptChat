package com.example.cryptchat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cryptchat.MessageActivity;
import com.example.cryptchat.Model.Chat;
import com.example.cryptchat.Model.User;
import com.example.cryptchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context context;
    private List<Chat>mChats;
    private String imageURL;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, List<Chat>cs, String imageURL) {
        this.context = context;
        this.mChats = cs;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Chat chat = mChats.get(position);

        //Log.i("TEST",holder.show_message.getText().toString());
        holder.show_message.setText(chat.getMessage());


        if(imageURL.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context).load(imageURL)
                    .into(holder.profile_image);
        }

        //check for last message
        if(position == mChats.size()-1){
            if(chat.isIsseen()){
                holder.txt_seen.setText("Seen");
            }else{
                holder.txt_seen.setText("Deliveted");
            }
        }else{
            holder.txt_seen.setVisibility(View.GONE);
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView profile_image;

        public TextView txt_seen;


        public ViewHolder(View item){
            super(item);
            show_message = item.findViewById(R.id.show_message);
            profile_image = item.findViewById(R.id.profile_image);
            txt_seen = item.findViewById(R.id.txt_seen);
        }
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }


    @Override
    public int getItemViewType(int position) {
       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       if(mChats.get(position).getSender().equals(firebaseUser.getUid())){
           return MSG_TYPE_RIGHT;
       }else {
           return MSG_TYPE_LEFT;
       }
    }
}
