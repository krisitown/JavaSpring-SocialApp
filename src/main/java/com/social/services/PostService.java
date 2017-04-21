package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import org.springframework.stereotype.Service;

public interface PostService {
    void create(PostCreationModel postModel, User currentUser);
}
