package com.social.repositories;

import com.social.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("SELECT m FROM Message as m WHERE (m.receiver.id = (:userOne) OR m.receiver.id = (:userTwo)) AND " +
            "(m.sender.id = (:userOne) OR m.sender.id = (:userTwo)) ORDER BY m.id DESC")
    List<Message> getMessagesBetweenUsers(@Param("userOne") long userIdOne, @Param("userTwo") long userIdTwo,
                                          Pageable pageable);
}
