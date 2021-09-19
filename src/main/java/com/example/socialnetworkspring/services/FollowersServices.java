package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Followers;
import com.example.socialnetworkspring.models.LikePost;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;

import java.util.List;

public interface FollowersServices {

    Followers addFollower(Followers followers);
    void deleteFollower(Followers followers);
    Followers getFollower(Users follower, Users subscriber);
    Long getCountFollowers(Users user);
    Long getCountSubscribers(Users user);
    List<Followers> getAllFollowersByUser(Users user);
    List<Followers> getAllSubscribersByUser(Users user);


}
