package com.social.models.bindingModels;


import javax.validation.constraints.Size;

public class CommentCreationModel {
    @Size(min = 5, max = 140, message = "Comment must be between 5 and 140 characters long.")
    private String content;

    private long originPostId;

    public long getOriginPostId() {
        return originPostId;
    }

    public void setOriginPostId(long originPostId) {
        this.originPostId = originPostId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
