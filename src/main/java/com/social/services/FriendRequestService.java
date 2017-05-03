package com.social.services;

import com.social.entities.User;
import com.social.models.viewModels.FriendRequestViewModel;

import java.util.List;

public interface FriendRequestService {
    void sendRequest(User sender, long receiverId);

    void acceptRequest(User currentUser, long friendRequestId);

    List<FriendRequestViewModel> getFriendRequests(User currentUser);
}
