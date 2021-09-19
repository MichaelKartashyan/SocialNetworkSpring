package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Comment;
import com.example.socialnetworkspring.models.LikePost;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;

import java.util.List;

public interface LikePostServices {
    LikePost addLikePost(LikePost likePost);
    void deleteLikePost(LikePost likePost);
    LikePost getLikePost(Post post, Users user);
   //LikePost getLikePost(Long postId,Long userId);
    Long getCountLike(Post post);
    List<LikePost> getAllLikesByPost(Post post);
    void deleteAllLikesByPost(Post post);
}
