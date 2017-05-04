package com.social.controllers;

import com.social.entities.User;
import com.social.errors.Errors;
import com.social.models.bindingModels.RegistrationModel;
import com.social.models.viewModels.UserInFriendListViewModel;
import com.social.models.viewModels.UserViewModel;
import com.social.services.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BasicUserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.register(registrationModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }

    @GetMapping("/users")
    public ModelAndView getUsers(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserViewModel> users = this.userService.getUsers(currentUser);
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/friends")
    public ResponseEntity<List<UserInFriendListViewModel>> getFriendsList(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserInFriendListViewModel> friendsList = userService.getFriendList(currentUser);
        return new ResponseEntity(friendsList, HttpStatus.OK);
    }

}
