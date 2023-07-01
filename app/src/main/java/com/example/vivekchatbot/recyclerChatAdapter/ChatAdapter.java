package com.example.vivekchatbot.recyclerChatAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.models.Chat;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    Context context;
    ArrayList<Chat> arrayList;

    public ChatAdapter(Context context, ArrayList<Chat> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_design, parent, false);
//         ChatViewHolder chatViewHolder = new ChatViewHolder(view);
        return new ChatViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.img.setImageResource(arrayList.get(position).img);
        holder.text1.setText(arrayList.get(position).text1);
        holder.text2.setText(arrayList.get(position).text2);
        holder.img2.setImageResource(arrayList.get(position).img2);
        holder.img4.setImageResource(arrayList.get(position).img4);
        holder.llchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_user_to_user_chat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView img , img2 , img4;
        TextView text1, text2;
        LinearLayout llchat;

        public ChatViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            llchat = itemView.findViewById(R.id.llchat);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            img2 = itemView.findViewById(R.id.img2);
            img4 = itemView.findViewById(R.id.img4);
        }
    }
}