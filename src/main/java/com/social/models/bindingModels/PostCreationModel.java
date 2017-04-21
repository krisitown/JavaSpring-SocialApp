package com.social.models.bindingModels;


import javax.validation.constraints.Size;

public class PostCreationModel {
    @Size(min = 5, max = 140, message = "Post must be between 5 and 140 characters long.")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
