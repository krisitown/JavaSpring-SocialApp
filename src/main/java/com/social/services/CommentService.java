package com.social.services;

import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.CommentCreationModel;
import com.social.models.viewModels.CommentViewModel;

import java.util.List;

public interface CommentService {
    void create(CommentCreationModel commentModel, User currentUser);

    List<CommentViewModel> getCommentsOfPost(Post post);
}
