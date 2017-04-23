package com.social.controllers;

import com.social.entities.User;
import com.social.models.bindingModels.CommentCreationModel;
import com.social.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/new")
    public String createComment(@Valid @ModelAttribute CommentCreationModel commentModel){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.create(commentModel, currentUser);
        return "redirect:/posts/" + commentModel.getOriginPostId();
    }
}
