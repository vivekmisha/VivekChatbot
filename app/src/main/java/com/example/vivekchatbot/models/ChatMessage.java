package com.example.vivekchatbot.models;

public class ChatMessage {
    static int messageCount= 0;

     int messageId;

     String messageText;

     boolean sentByUser;

     String recieverName;


    public static int getMessageCount() {
        return messageCount;
    }

    public static void setMessageCount(int messageCount) {
        ChatMessage.messageCount = messageCount;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public ChatMessage(int messageId, String messageText, boolean sentByUser, String recieverName) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.sentByUser = sentByUser;
        this.recieverName = recieverName;
    }
}
