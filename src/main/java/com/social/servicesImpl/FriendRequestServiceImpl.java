package com.social.servicesImpl;

import com.social.entities.FriendRequest;
import com.social.entities.User;
import com.social.exception.InsufficientAuthorityToAcceptFriendRequestException;
import com.social.models.viewModels.FriendRequestViewModel;
import com.social.repositories.BasicUserRepository;
import com.social.repositories.FriendRequestRepository;
import com.social.repositories.UserRepository;
import com.social.services.FriendRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void sendRequest(User sender, long receiverId) {
        User receiver = userRepository.findOne(receiverId);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setAccepted(false);
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void acceptRequest(User currentUser, long friendRequestId) {
        FriendRequest friendRequest = friendRequestRepository.findOne(friendRequestId);
        if(currentUser.getId() != friendRequest.getReceiver().getId()){
            throw new InsufficientAuthorityToAcceptFriendRequestException();
        }
        friendRequest.setAccepted(true);
        User sender = userRepository.findOne(friendRequest.getSender().getId());
        User receiver = userRepository.findOne(friendRequest.getReceiver().getId());
        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);
        userRepository.save(sender);
        userRepository.save(receiver);
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public List<FriendRequestViewModel> getFriendRequests(User currentUser) {
        List<FriendRequest> friendRequests = friendRequestRepository.getAllRelevantFriendRequestsOfUser(currentUser);
        List<FriendRequestViewModel> friendRequestViewModels = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequests) {
            FriendRequestViewModel friendRequestViewModel = modelMapper.map(friendRequest, FriendRequestViewModel.class);
            friendRequestViewModel.setUsername(friendRequest.getSender().getUsername());
            friendRequestViewModels.add(friendRequestViewModel);
        }
        return friendRequestViewModels;
    }
}
