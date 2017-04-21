package com.social.services;

import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;

public interface PostService {
    void create(PostCreationModel postModel, User currentUser);
    Iterable<Post> index();
}
