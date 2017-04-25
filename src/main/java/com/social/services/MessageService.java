package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.MessageCreationModel;

public interface MessageService {
    void send(MessageCreationModel messageModel, User currentUser);
}
