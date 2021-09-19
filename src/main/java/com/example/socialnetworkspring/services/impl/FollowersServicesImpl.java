package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.FollowersRepository;
import com.example.socialnetworkspring.models.Followers;
import com.example.socialnetworkspring.models.Users;
import com.example.socialnetworkspring.services.FollowersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowersServicesImpl implements FollowersServices {

    @Autowired
    FollowersRepository followersRepository;

    @Override
    public Followers addFollower(Followers followers) {
        return followersRepository.save(followers);
    }

    @Override
    public void deleteFollower(Followers followers) {
        followersRepository.delete(followers);
    }

    @Override
    public Followers getFollower(Users follower, Users subscriber) {
        return followersRepository.findByFollowerAndSubscriber(follower,subscriber);
    }

    @Override
    public Long getCountFollowers(Users user) {
        return followersRepository.countAllByFollower(user);
    }

    @Override
    public Long getCountSubscribers(Users user) {
        return followersRepository.countAllBySubscriber(user);
    }

    @Override
    public List<Followers> getAllFollowersByUser(Users user) {
        return followersRepository.findAllByFollower(user);
    }

    @Override
    public List<Followers> getAllSubscribersByUser(Users user) {
        return followersRepository.findAllBySubscriber(user);
    }


}
