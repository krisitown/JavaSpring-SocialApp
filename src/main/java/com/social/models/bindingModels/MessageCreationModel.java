package com.social.models.bindingModels;

import javax.validation.constraints.Size;

public class MessageCreationModel {
    @Size(min = 1, max = 300, message = "Message should be between 1 and 300 characters.")
    private String content;

    private long receiver;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }
}
