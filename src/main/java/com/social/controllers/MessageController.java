package com.social.controllers;

import com.social.entities.User;
import com.social.models.bindingModels.MessageCreationModel;
import com.social.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/messages/new")
    public String sendMessage(@Valid @ModelAttribute MessageCreationModel messageModel){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageService.send(messageModel, currentUser);
        return "redirect:/"; //todo: change this concerning JSON
    }
}
