package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.CommentCreationModel;

public interface CommentService {
    void create(CommentCreationModel commentModel, User currentUser);
}
