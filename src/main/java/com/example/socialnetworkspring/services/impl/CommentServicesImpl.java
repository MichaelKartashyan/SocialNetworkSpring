package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.CommntsRepository;
import com.example.socialnetworkspring.models.Comment;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.services.CommentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServicesImpl implements CommentsServices {

    @Autowired
    CommntsRepository commntsRepository;

    @Override
    public List<Comment> getAllCommentsForPost(Long id) {
        return commntsRepository.findAllByPostIdOrderByIdDesc(id);
    }

    @Override
    public Comment getComment(Long id) {
        return commntsRepository.getById(id);
    }

    @Override
    public void deleteComment(Long id) {
        commntsRepository.deleteById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commntsRepository.save(comment);
    }

    @Override
    public Comment addComment(Comment comment) {
        return commntsRepository.save(comment);
    }

    @Override
    public void delComm(Comment comment) {
        commntsRepository.delete(comment);

    }

    @Override
    public Long countAllCommentsByPost(Long id) {
        return commntsRepository.countAllByPostId(id);
    }

    @Override
    public Comment getCommentByPost(Long id) {
        return commntsRepository.findFirstByPostId(id);
    }

    @Override
    public void delAllCommentsByPostId(Long id) {
        commntsRepository.deleteAllByPostId(id);
    }


}
