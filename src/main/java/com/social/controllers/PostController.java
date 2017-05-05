package com.social.controllers;

import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import com.social.models.viewModels.PostViewModel;
import com.social.models.viewModels.UserInFriendListViewModel;
import com.social.services.BasicUserService;
import com.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts/new")
    public String createPost(@Valid @ModelAttribute PostCreationModel postModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "index";
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postService.create(postModel, currentUser);
        return "redirect:/";
    }

    @GetMapping("/posts")
    public ModelAndView index(){
        List<PostViewModel> posts = postService.index();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("posts", posts);
        return mav;
    }
}
