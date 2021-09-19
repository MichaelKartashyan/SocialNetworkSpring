package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByForUsersOrderByIdDesc(Long id);

//    @Query(value = "SELECT p.id, p.postText, p.date,p.countLike,p.forUsers," +
//            " u.id , u.name, u.surname,u.imagePath, " +
//            " f.id , f.follower, f.subscriber" +
//            " from Post p " +
//            "inner join Users u on p.user.id = u.id" +
//            " INNER JOIN Followers f on p.forUsers = f.subscriber.id  where f.follower= ?1")
    @Query(value = "SELECT * " +
            " from post p " +
            " inner join users u on p.user_id = u.id " +
            " INNER JOIN followers f on p.for_users = f.subscriber_id  where f.follower_id= ?1",
    nativeQuery = true)
    List<Post> findAllPost(Users user);

}
