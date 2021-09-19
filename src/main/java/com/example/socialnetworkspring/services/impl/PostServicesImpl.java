package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.PostRepository;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;
import com.example.socialnetworkspring.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServicesImpl implements PostServices {
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> getAllPostsByUser(Long id) {
        return postRepository.findAllByForUsersOrderByIdDesc(id);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delPost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> getAllPostsByHomePage(Users user) {
        return postRepository.findAllPost(user);
    }


}
