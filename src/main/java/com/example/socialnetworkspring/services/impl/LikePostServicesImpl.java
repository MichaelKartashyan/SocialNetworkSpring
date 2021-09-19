package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.LikePostRepository;
import com.example.socialnetworkspring.models.LikePost;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;
import com.example.socialnetworkspring.services.LikePostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikePostServicesImpl implements LikePostServices {
    @Autowired
    LikePostRepository likePostRepository;



    @Override
    public LikePost addLikePost(LikePost likePost) {
        return likePostRepository.save(likePost);
    }

    @Override
    public void deleteLikePost(LikePost likePost) {
        likePostRepository.delete(likePost);
    }

//    @Override
//    public LikePost getLikePost(Long postId, Long userId) {
//        return likePostRepository.findByPost_IdAndUserId(postId,userId);
//    }

    @Override
    public LikePost getLikePost(Post post, Users user) {
        return likePostRepository.findByPostAndUser(post,user);
    }



    @Override
    public Long getCountLike(Post post) {
        return likePostRepository.countAllByPost(post);
    }

    @Override
    public List<LikePost> getAllLikesByPost(Post post) {
        return likePostRepository.findAllByPost(post);
    }

    @Override
    public void deleteAllLikesByPost(Post post) {
        likePostRepository.deleteAllByPost(post);
    }


}
