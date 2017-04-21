package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import com.social.models.viewModels.PostViewModel;

import java.util.List;

public interface PostService {
    void create(PostCreationModel postModel, User currentUser);
    List<PostViewModel> index();
}
