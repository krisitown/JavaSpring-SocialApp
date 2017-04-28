package com.social.models.bindingModels;

public class MessageCreationModel {
    private String content;

    private long sender;

    private long receiver;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long senderId) {
        this.sender = senderId;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiverId) {
        this.receiver = receiverId;
    }
}
