package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Comment;
import com.example.socialnetworkspring.models.Post;

import java.util.List;

public interface CommentsServices {
    List<Comment> getAllCommentsForPost(Long id);
    Comment getComment(Long id);
    void deleteComment(Long id);
    Comment saveComment(Comment comment);
    Comment addComment(Comment comment);
    void delComm(Comment comment);
    Long countAllCommentsByPost(Long id);
    Comment getCommentByPost(Long id);
    void delAllCommentsByPostId(Long id);
}
