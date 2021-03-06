package com.social.controllers;

import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return "redirect:/posts/";
    }
}
