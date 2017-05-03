package com.social.services;

import com.social.entities.User;
import com.social.models.bindingModels.RegistrationModel;
import com.social.models.viewModels.UserInFriendListViewModel;
import com.social.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    List<UserInFriendListViewModel> getFriendList(User user);

    List<UserViewModel> getUsers(User currentUser);
}