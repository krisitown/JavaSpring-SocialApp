package com.social.servicesImpl;

import com.social.entities.Message;
import com.social.entities.User;
import com.social.models.bindingModels.MessageCreationModel;
import com.social.models.viewModels.MessageViewModel;
import com.social.repositories.BasicUserRepository;
import com.social.repositories.MessageRepository;
import com.social.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BasicUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void send(MessageCreationModel messageModel, User currentUser) {
        Message message = modelMapper.map(messageModel, Message.class);
        message.setSender(currentUser);
        message.setReceiver(userRepository.findOne(messageModel.getReceiver()));
        messageRepository.save(message);
    }

    @Override
    public List<MessageViewModel> getMessagesBetween(User userOne, User userTwo) {
        List<Message> messages = messageRepository.getMessagesBetweenUsers(userOne.getId(), userTwo.getId());
        List<MessageViewModel> messageViewModels = new ArrayList<>();
        for (Message message : messages) {
            MessageViewModel messageModel = modelMapper.map(message, MessageViewModel.class);
            messageViewModels.add(messageModel);
        }
        return messageViewModels;
    }
}
