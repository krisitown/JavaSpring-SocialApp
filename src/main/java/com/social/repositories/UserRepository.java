package com.social.repositories;

import com.social.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository<T extends User> extends CrudRepository<T, Long> {
    User findOne(long id);

    @Query("SELECT u FROM User as u JOIN FETCH u.friends WHERE u.id = (:userId)")
    User getUserAndFetchFriends(@Param("userId") long id);

    T findOneByUsername(String username);
}
