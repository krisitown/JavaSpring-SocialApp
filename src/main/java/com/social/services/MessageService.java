package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.MessageCreationModel;
import com.social.models.viewModels.MessageViewModel;

import java.util.List;

public interface MessageService {
    void send(MessageCreationModel messageModel, User currentUser);

    List<MessageViewModel> getMessagesBetween(User userOne, User userTwo, String lastMessage);
}
