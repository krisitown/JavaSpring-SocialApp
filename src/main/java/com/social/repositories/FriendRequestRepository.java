package com.social.repositories;


import com.social.entities.FriendRequest;
import com.social.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {
    @Query("SELECT fr FROM FriendRequest AS fr WHERE fr.receiver = (:user) AND fr.isAccepted = false")
    List<FriendRequest> getAllRelevantFriendRequestsOfUser(@Param("user") User user);
}
