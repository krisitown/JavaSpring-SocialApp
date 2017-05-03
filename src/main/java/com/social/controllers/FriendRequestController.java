package com.social.controllers;

import com.social.entities.BasicUser;
import com.social.entities.User;
import com.social.models.viewModels.FriendRequestViewModel;
import com.social.models.viewModels.UserInFriendListViewModel;
import com.social.services.BasicUserService;
import com.social.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FriendRequestController {
    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private BasicUserService userService;

    @PostMapping("/send_request")
    public String sendRequest(@RequestParam("userId")long userId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendRequestService.sendRequest(currentUser, userId);
        return "redirect:/";
    }

    @PostMapping("/accept_request")
    public String acceptRequest(@RequestParam("requestId") long requestId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendRequestService.acceptRequest(currentUser, requestId);
        return "redirect:/";
    }

    @GetMapping("/friend_requests")
    public ModelAndView getRelevantFriendRequests(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FriendRequestViewModel> friendRequests = friendRequestService.getFriendRequests(currentUser);
        List<UserInFriendListViewModel> friends = userService.getFriendList(currentUser);
        ModelAndView mav = new ModelAndView("friend-requests");
        mav.addObject("friendRequests", friendRequests);
        mav.addObject("friends", friends);
        return mav;
    }
}
