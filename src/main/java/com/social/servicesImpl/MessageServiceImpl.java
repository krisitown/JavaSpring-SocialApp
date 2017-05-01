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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<MessageViewModel> getMessagesBetween(User userOne, User userTwo, String lastMessage) {
        List<Message> messages = messageRepository.getMessagesBetweenUsers(userOne.getId(), userTwo.getId(),
                new PageRequest(0, 30));
        List<MessageViewModel> messageViewModels = new ArrayList<>();
        for (Message message : messages) {
            if(message.getContent().equals(lastMessage)){
                break;
            }
            MessageViewModel messageModel = modelMapper.map(message, MessageViewModel.class);
            messageModel.setName(message.getSender().getUsername());
            messageViewModels.add(messageModel);
        }
        Collections.reverse(messageViewModels);
        return messageViewModels;
    }
}
