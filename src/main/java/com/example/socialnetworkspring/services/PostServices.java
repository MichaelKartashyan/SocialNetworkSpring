package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;

import java.util.List;

public interface PostServices {
    List<Post> getAllPostsByUser(Long id);
    Post getPost(Long id);
    void deletePost(Long id);
    Post savePost(Post post);
    Post addPost(Post post);
    void delPost(Post post);
    List<Post> getAllPostsByHomePage(Users user);
}
