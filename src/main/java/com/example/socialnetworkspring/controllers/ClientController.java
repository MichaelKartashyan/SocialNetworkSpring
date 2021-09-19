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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
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



    @GetMapping(value = "/userprofile/{userId}")
    public String userProfile(Model model, @PathVariable(name="userId") Long id){
        Users user = getUser();
        Users thisUser = userServices.getUserById(id);
        //List<Post> posts = postServices.getAllPostsByUser(thisUser.getId());
        Followers followers = followersServices.getFollower(user,thisUser);
        if(followers!=null){
            model.addAttribute("followers",followers);
        }
        //model.addAttribute("posts",posts);
        model.addAttribute("user",user);
        model.addAttribute("thisUser",thisUser);

        return "userprofile";
    }


    @GetMapping(value = "/userslist")
    public String usersList(Model model){
        Users user = getUser();
        List<Users> users = userServices.getAllUsers();

        model.addAttribute("user",user);
        model.addAttribute("users",users);

        return "userslist";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/addpostuserprofile")
    public String addPost(@RequestParam(name = "textPost")String textPost,
                          @RequestParam(name = "forUserId")Long forUserId,
                          HttpServletRequest httpServletRequest) {
        Users user = getUser();
        Post post = new Post();
        post.setPostText(textPost);
        post.setCountLike(0);
        post.setCountComment(0);
        post.setDate(new Timestamp(System.currentTimeMillis()));
        post.setForUsers(forUserId);
        post.setUser(user);
        postServices.addPost(post);
        String referer = httpServletRequest.getHeader("Referer");
        return "redirect:" + referer;
        //return "redirect:/userprofile/"+forUserId;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/addcommentuserprofile")
    public String addComment(@RequestParam(name = "postId")Long postId,
                             @RequestParam(name = "textComment")String textComment,
                             HttpServletRequest httpServletRequest){
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
        String referer = httpServletRequest.getHeader("Referer");
        return "redirect:" + referer;
        //return "redirect:/userprofile/"+post.getForUsers();
    }




    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/likePostUserProfile")
    public String likePostUserProfile(@RequestParam(name = "postId")Long postId,
                           @RequestParam(name = "userId")Long userId,
                                      HttpServletRequest httpServletRequest){
        Post post = postServices.getPost(postId);
        Users user = userServices.getUserById(userId);
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

        String referer = httpServletRequest.getHeader("Referer");
        return "redirect:" + referer;
    }




    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deleteCommentUserProfile")
    public String deleteComment(@RequestParam(name = "commentId")Long commentId,
                                @RequestParam(name = "postId")Long postId,
                                HttpServletRequest httpServletRequest){
        Post post = postServices.getPost(postId);
        Comment comment = commentsServices.getComment(commentId);
        //Comment commentPost = post.getComment();
        String referer = httpServletRequest.getHeader("Referer");
//        if(comment==commentPost){
//            Long countComment = commentsServices.countAllCommentsByPost(postId);
//            if(countComment>1){
//                Comment firstComment = commentsServices.getCommentByPost(postId);
//                if(firstComment==comment){
//                    firstComment=null;
//                }
//                post.setComment(firstComment);
//                postServices.savePost(post);
//                commentsServices.delComm(comment);
//                return "redirect:" + referer;
//                //return "redirect:/userprofile/"+post.getForUsers();
//            }else{
//
//                post.setComment(null);
//                comment.setPost(null);
//                postServices.savePost(post);
//                commentsServices.saveComment(comment);
//                commentsServices.delComm(comment);
//                return "redirect:" + referer;
//                //return "redirect:/userprofile/"+post.getForUsers();
//            }
//
//        }else{
            commentsServices.delComm(comment);
        Long countComment = commentsServices.countAllCommentsByPost(postId);
        post.setCountComment(countComment.intValue());
        postServices.savePost(post);
            return "redirect:" + referer;
            //return "redirect:/userprofile/"+post.getForUsers();
        //}

    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deletePostUserProfile")
    public String deletePost(@RequestParam(name = "postId")Long postId,
                             HttpServletRequest httpServletRequest){
        Post post = postServices.getPost(postId);
        //post.setComment(null);
        postServices.savePost(post);
        Long id = post.getForUsers();
        likePostServices.deleteAllLikesByPost(post);
        commentsServices.delAllCommentsByPostId(postId);
        postServices.deletePost(postId);
        String referer = httpServletRequest.getHeader("Referer");
        return "redirect:" + referer;
        //return "redirect:/userprofile/"+id;
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/postDetails/{postId}")
    public String postDetails(Model model, @PathVariable(name = "postId")Long postId){

        Post post = postServices.getPost(postId);
        Users user = getUser();
        List<Comment> comments = commentsServices.getAllCommentsForPost(postId);
        model.addAttribute("comments" , comments);
        model.addAttribute("post",post);
        model.addAttribute("user",user);
        return "postDetails";
        }



        @PreAuthorize("isAuthenticated()")
        @PostMapping(value = "/subscribe")
        public String subscribe(@RequestParam(name = "followersId")Long followersId,
                                @RequestParam(name = "subscribersId")Long subscribersId,
                                 HttpServletRequest httpServletRequest){

        Users follower = userServices.getUserById(followersId);
        Users subscriber = userServices.getUserById(subscribersId);
        Followers followers = followersServices.getFollower(follower,subscriber);
        if(followers!=null){
            followersServices.deleteFollower(followers);
            Long countFollow = followersServices.getCountFollowers(subscriber);
            Long countSubscriber = followersServices.getCountSubscribers(subscriber);
            subscriber.setSubscribersCount(countSubscriber.intValue());
            subscriber.setFollowersCount(countFollow.intValue());
            userServices.updateUser(subscriber);

            Long fCountFollow = followersServices.getCountFollowers(follower);
            Long fCountSubscriber = followersServices.getCountSubscribers(follower);
            follower.setSubscribersCount(fCountSubscriber.intValue());
            follower.setFollowersCount(fCountFollow.intValue());
            userServices.updateUser(follower);
        }else{
            Followers followers1 = new Followers();
            followers1.setFollower(follower);
            followers1.setSubscriber(subscriber);
            followersServices.addFollower(followers1);
            Long countFollow = followersServices.getCountFollowers(subscriber);
            Long countSubscriber = followersServices.getCountSubscribers(subscriber);
            subscriber.setSubscribersCount(countSubscriber.intValue());
            subscriber.setFollowersCount(countFollow.intValue());
            userServices.updateUser(subscriber);

            Long fCountFollow = followersServices.getCountFollowers(follower);
            Long fCountSubscriber = followersServices.getCountSubscribers(follower);
            follower.setSubscribersCount(fCountSubscriber.intValue());
            follower.setFollowersCount(fCountFollow.intValue());
            userServices.updateUser(follower);
        }
            String referer = httpServletRequest.getHeader("Referer");
            return "redirect:" + referer;
        }



    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/followers")
    public String followers(Model model){

        Users user = getUser();
        List<Followers> followers = followersServices.getAllFollowersByUser(user);
        model.addAttribute("user",user);
        model.addAttribute("followers",followers);
        return "followers";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/subscribers")
    public String subscribers(Model model){

        Users user = getUser();
        List<Followers> subscribers = followersServices.getAllSubscribersByUser(user);

        model.addAttribute("user",user);
        model.addAttribute("subscribers",subscribers);
        return "subscribers";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "dialog/{userId}")
    public String  dialog(Model model, @PathVariable(name = "userId")Long userId){
        Users user1 = getUser();
        Users user2= userServices.getUserById(userId);
        model.addAttribute("user",user1);
        model.addAttribute("user2",user2);
        return "dialog";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "dialogsList")
    public String  dialogsList(Model model){
        Users user = getUser();

        List<Message> dialogs = messageServices.getAllDialogs(user);
        model.addAttribute("user",user);
        model.addAttribute("dialogs",dialogs);
        return "dialogsList";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/search")
    public String search(Model model,@RequestParam(value = "searchResult")String searchResult){
        Users user = getUser();
        String[] nameSurname;
        List<Users> searchUsers = new ArrayList<>();
        nameSurname = searchResult.split(" ");
        if(nameSurname.length>1){
            searchUsers = userServices.getSearchUsers(nameSurname[0],nameSurname[1]);
        }else if(nameSurname.length<=1){
            searchUsers = userServices.getSearchUsersByName(nameSurname[0]);
        }
        model.addAttribute("searchUsers",searchUsers);
        model.addAttribute("user",user);

        return "search";
    }




    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }
}
