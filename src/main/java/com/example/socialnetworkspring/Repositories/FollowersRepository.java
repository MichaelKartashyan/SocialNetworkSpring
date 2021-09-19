package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Followers;
import com.example.socialnetworkspring.models.LikePost;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers,Long> {

    Long countAllBySubscriber(Users users);
    Long countAllByFollower(Users user);
    List<Followers> findAllByFollower(Users users);
    List<Followers> findAllBySubscriber(Users users);
    Followers findByFollowerAndSubscriber(Users follower,Users subscriber);
    //List<Users> findAllByFollower(Users users);

}
