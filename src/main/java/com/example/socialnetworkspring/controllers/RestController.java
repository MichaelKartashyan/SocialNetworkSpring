package com.example.socialnetworkspring.controllers;


import com.example.socialnetworkspring.models.*;
import com.example.socialnetworkspring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;


@org.springframework.web.bind.annotation.RestController

public class RestController {

    @Autowired
    UserServices userServices;

    @Autowired
    PostServices postServices;

    @Autowired
    CommentsServices commentsServices;

    @Autowired
    LikePostServices likePostServices;

    @Autowired
    FollowersServices followersServices;

    @Autowired
    MessageServices messageServices;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/ajaxhome/{userId}")
    public ResponseEntity<List<Post>> ajaxHome(Model model , @PathVariable(name="userId") Long userId){
    Users user = userServices.getUserById(userId);
    List<Post> posts = postServices.getAllPostsByHomePage(user);
    return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/ajaxLikePost/{postId}")
    public ResponseEntity<String> ajaxLikePost(@PathVariable(name = "postId")Long postId){
        Post post = postServices.getPost(postId);
        Users user = getUser();
        Long countLike = likePostServices.getCountLike(post);
        LikePost likePost = likePostServices.getLikePost(post,user);
        if(likePost!=null){
            likePostServices.deleteLikePost(likePost);
            countLike = likePostServices.getCountLike(post);
            post.setCountLike(countLike.intValue());
            postServices.savePost(post);
        }else{
            LikePost likePost1 = new LikePost();
            likePost1.setPost(post);
            likePost1.setUser(user);
            likePostServices.addLikePost(likePost1);
            countLike = likePostServices.getCountLike(post);
            post.setCountLike(countLike.intValue());
            postServices.savePost(post);
        }

        return new ResponseEntity<>("OK",HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/ajaxAddComment")
    public ResponseEntity<String> ajaxAddComment(@RequestParam(name = "textComment")String textComment,
                                          @RequestParam(name = "postId")Long postId){
        Users user = getUser();
        Post post = postServices.getPost(postId);
        Comment comment = new Comment();
        comment.setComment(textComment);
        comment.setDate(new Timestamp(System.currentTimeMillis()));
        comment.setUser(user);
        comment.setPost(post);
        //post.setComment(comment);
        commentsServices.addComment(comment);
        Long countComment = commentsServices.countAllCommentsByPost(postId);
        post.setCountComment(countComment.intValue());
        postServices.savePost(post);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "ajaxDeletePost/{postId}")
    public ResponseEntity<String> ajaxDeletePost(@PathVariable(name = "postId")Long postId){
        Post post = postServices.getPost(postId);
        postServices.savePost(post);
        likePostServices.deleteAllLikesByPost(post);
        commentsServices.delAllCommentsByPostId(postId);
        postServices.deletePost(postId);
        return new ResponseEntity<>("Пост удален", HttpStatus.OK);
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/ajaxMyPage")
    public ResponseEntity<List<Post>> ajaxMyPage(Model model ){
        Users user = getUser();
        List<Post> posts = postServices.getAllPostsByUser(user.getId());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/ajaxAddPost")
    public ResponseEntity<String> ajaxAddPost(@RequestParam(name = "textPost")String textPost,
                                                 @RequestParam(name = "forUsers")Long forUsers){
        Users user = getUser();
        Post post = new Post();
        post.setPostText(textPost);
        post.setCountLike(0);
        post.setCountComment(0);
        post.setDate(new Timestamp(System.currentTimeMillis()));
        post.setForUsers(forUsers);
        post.setUser(user);

        postServices.addPost(post);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/ajaxUserProfile/{thisUser}")
    public ResponseEntity<List<Post>> ajaxMyPage(Model model,@PathVariable(name = "thisUser")Long id){
        Users user = getUser();
        Users thisUser = userServices.getUserById(id);
        List<Post> posts = postServices.getAllPostsByUser(thisUser.getId());
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/ajaxGetDialog/{userId}")
    public ResponseEntity<List<Message>> dialog(Model model, @PathVariable(name = "userId")Long userId){
        Users user1 = getUser();
        Users user2= userServices.getUserById(userId);

        List<Message> messages = messageServices.getAllMessagesFromUsers(user1,user2);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/ajaxWriteMassage")
    public ResponseEntity<String> ajaxWriteMassage(@RequestParam(name = "messageText")String messageText,
                                              @RequestParam(name = "user2")Long userId){
        Users user1 = getUser();
        Users user2 = userServices.getUserById(userId);
        Message message = new Message();
        message.setMessage(messageText);
        message.setUser1(user1);
        message.setUser2(user2);
        message.setDate(new Timestamp(System.currentTimeMillis()));

        messageServices.saveMessage(message);
        return new ResponseEntity<>("Ok",HttpStatus.OK);
    }




    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

}
