package com.social.repositories;

import com.social.entities.BasicUser;
import com.social.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicUserRepository extends UserRepository<BasicUser> {
    @Query("SELECT u FROM User as u JOIN FETCH u.friends WHERE u.id = (:userId)")
    User getUserAndFetchFriends(@Param("userId") long id);
}
