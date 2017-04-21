package com.social.controllers;

import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import com.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts/new")
    public String createPost(@Valid @ModelAttribute PostCreationModel postModel, HttpSession session){
        User currentUser = (User) session.getAttribute("current_user");
        postService.create(postModel, currentUser);
        return "redirect:/";
    }
}
