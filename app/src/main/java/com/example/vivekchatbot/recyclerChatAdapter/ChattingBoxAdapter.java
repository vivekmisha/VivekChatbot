package com.example.vivekchatbot.recyclerChatAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.models.ChatMessage;
import com.example.vivekchatbot.models.DateMessage;

import java.util.List;

public class ChattingBoxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
//    ArrayList<Object> arrayList;

    List<ChatMessage>  chatMessages;
    private static final int VIEW_TYPE_MESSAGE_SEND = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_DATE = 3;


    public ChattingBoxAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
//        this.arrayList = arrayList;
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MESSAGE_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.senderchat,parent,false);
            return new SenderChattingViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED){
            View view = LayoutInflater.from(context).inflate(R.layout.recieverchat,parent,false);
            return new ReceiverChattingViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.date_design,parent,false);

            return new DateViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        ChatMessage item = chatMessages.get(position);
        if (holder instanceof SenderChattingViewHolder)
        {
            ChatMessage chatMessage = (ChatMessage) item;
            ((SenderChattingViewHolder) holder).bindMessage(chatMessage);
        } else if (holder instanceof ReceiverChattingViewHolder) {
            ChatMessage chatMessage = (ChatMessage) item;
            ((ReceiverChattingViewHolder) holder).bindMessage(chatMessage);
        } else if (holder instanceof DateViewHolder) {
//            DateMessage dateMessage = (DateMessage) item;
//            ((DateViewHolder) holder).bindMessage(dateMessage);
        }
    }


    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
      ChatMessage item = chatMessages.get(position);
        if (item instanceof ChatMessage) {
            if (((ChatMessage) item).isSentByUser()) {
                return VIEW_TYPE_MESSAGE_SEND;
            } else return VIEW_TYPE_MESSAGE_RECEIVED;
        } else return VIEW_TYPE_DATE;
    }

    public static class SenderChattingViewHolder extends RecyclerView.ViewHolder {
         TextView sender_chat_message,sender_chat_time;

        public SenderChattingViewHolder(@NonNull View itemView) {
            super(itemView);
            sender_chat_message = itemView.findViewById(R.id.sender_message_text);
            sender_chat_time = itemView.findViewById(R.id.sender_message_text2);
        }

        public void bindMessage(ChatMessage chatMessage)
        {
            sender_chat_message.setText(chatMessage.getMessageText());
        }

    }

    public static class ReceiverChattingViewHolder extends RecyclerView.ViewHolder {
        TextView receiver_chat_message,receiver_chat_time;
        public ReceiverChattingViewHolder(@NonNull View itemView) {
            super(itemView);
            receiver_chat_message = itemView.findViewById(R.id.receiver_message_text);
            receiver_chat_time = itemView.findViewById(R.id.receiver_message_text2);
        }
        public void bindMessage(ChatMessage chatMessage)
        {
            receiver_chat_message.setText(chatMessage.getMessageText());
//            receiver_chat_time.setText(chatMessage.get);
        }

    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateText);
        }
        public void bindMessage(DateMessage dateMessage)
        {
         date.setText(dateMessage.getDate());
        }

    }
}