package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Message;
import com.example.socialnetworkspring.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query(value = "SELECT * from message " +
            "where user1_id = ?1 and user2_id = ?2 " +
            "OR user2_id = ?1 and user1_id = ?2",
            nativeQuery = true)
    List<Message> findAllByUser1AndUser2(Users user1, Users user2);

    @Query(value = "SELECT * FROM message" +
            " where user1_id = ?1 or user2_id = ?1 GROUP BY user2_id",
            nativeQuery = true)
    List<Message> findListDialogs(Users user1);

}
