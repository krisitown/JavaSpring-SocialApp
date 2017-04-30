package com.social.controllers;

import com.social.entities.User;
import com.social.models.bindingModels.MessageCreationModel;
import com.social.models.viewModels.MessageViewModel;
import com.social.repositories.BasicUserRepository;
import com.social.repositories.UserRepository;
import com.social.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private BasicUserRepository userRepository;

    @PostMapping("/messages/new")
    public String sendMessage(@Valid @ModelAttribute MessageCreationModel messageModel){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageService.send(messageModel, currentUser);
        return "redirect:/"; //todo: change this concerning JSON
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<MessageViewModel>> getMessagesWith(@PathVariable("id") long userId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MessageViewModel> messages = messageService.getMessagesBetween(currentUser, (User)userRepository.findOne(userId));
        return new ResponseEntity(messages, HttpStatus.OK);
    }
}
