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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    UserServices userServices;

    @Autowired
    PostServices postServices;

    @Autowired
    CommentsServices commentsServices;

    @Autowired
    LikePostServices likePostServices;

    @Autowired
    RolesServices rolesServices;

    @Autowired
    PasswordEncoder passwordEncoder;




    @PreAuthorize("isAnonymous()")
    @GetMapping(value = {"/loginpage", "/", "/index"})
    public String loginPage(Model model){
        //model.addAttribute("CURRENT_USER", getUser());
        return "loginpage";
    }


    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "register")
    public String register(@RequestParam(name = "name")String name,
                           @RequestParam(name = "surname")String surname,
                           @RequestParam(name = "day")String day,
                           @RequestParam(name = "month")String month,
                           @RequestParam(name = "year")String year,
                           @RequestParam(name = "email")String email,
                           @RequestParam(name = "sex")String sex,
                           @RequestParam(name = "password")String password){
        Users users = new Users();
        if(userServices.isFreeEmail(email)){
            String birthdate = day+" "+month + " "+ year;
            String defaults = "Не указано";
            users.setSurname(surname);
            users.setBirthdate(birthdate);
            users.setUniversity(defaults);
            users.setAbout(defaults);
            users.setCity(defaults);
            users.setGender(sex);
            users.setImagePath("avatarDefault.jpg");
            users.setFollowersCount(0);
            users.setPhone(0);
            users.setPostCount(0);
            users.setSubscribersCount(0);
            users.setName(name);
            users.setPassword(passwordEncoder.encode(password));
            users.setEmail(email);
            Set<Roles> roles = rolesServices.getRoleByName("ROLE_USER");
            users.setRoles(roles);
            userServices.addUser(users);
            return "/loginpage";

        }else{
            return "redirect:/registerpage?error";
        }

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/home")
        public String home(Model model){
        Users user = getUser();
        model.addAttribute("user",user);
//        List<Post> posts = postServices.getAllPostsByHomePage(user);
//        System.out.println(posts);
        return "/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/mypage")
    public String Mypage(Model model){
        Users user = getUser();
        model.addAttribute("user",user);
//        List<Post> posts = postServices.getAllPostsByUser(user.getId());
//        model.addAttribute("posts",posts);
        return "/mypage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/editmyprofile")
    public String EditMyProfile(Model model){
        Users user = getUser();
        model.addAttribute("user",user);

        return "/editmyprofile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "editprofile")
    public String EditProfile(@RequestParam(name = "uName")String name,
                              @RequestParam(name = "id")String id,
                              @RequestParam(name = "surname")String surname,
                              @RequestParam(name = "about")String about,
                              @RequestParam(name = "phone")int phone,
                              @RequestParam(name = "city")String city,
                              @RequestParam(name = "email")String email,
                              @RequestParam(name = "university")String university){
        Users users = getUser();

        if(name!=null&&surname!=null&&about!=null&&city!=null&&email!=null&&university!=null){
            if(users.getEmail().equals(email)){
                users.setSurname(surname);
                users.setUniversity(university);
                users.setAbout(about);
                users.setCity(city);
                users.setPhone(phone);
                users.setName(name);
                userServices.updateUser(users);
                return "redirect:/editmyprofile?success";
            }
           else if(userServices.isFreeEmail(email)){
                users.setSurname(surname);
                users.setUniversity(university);
                users.setAbout(about);
                users.setCity(city);
                users.setPhone(phone);
                users.setName(name);
                users.setEmail(email);
                userServices.updateUser(users);
                return "redirect:/editmyprofile?success";
            }else{
                return "redirect:/editmyprofile?error";
            }
        }else{
            return "redirect:/editmyprofile?error";
        }

    }


//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "editavatare")
//    public String EditAvatare(@RequestParam(name = "avatar")String avatar){
//        Users users = getUser();
//        if(avatar!=null){
//                users.setImagePath(avatar);
//                userServices.updateUser(users);
//                return "redirect:/editmyprofile?success";
//            }else{
//                return "redirect:/editmyprofile?error";
//            }
//    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "editAvatare")
    public ResponseEntity<?> EditAvatare(@RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        Users users = getUser();
        try{
            if(fileName!=null) {
                String uuidfile = UUID.randomUUID().toString();
                String resultFileName = uuidfile + "." + fileName;

                file.transferTo(new File("C:\\Users\\Михаил\\IdeaProjects\\SocialNetworkSpring\\src\\main\\resources\\static\\img\\" + resultFileName));
                users.setImagePath(resultFileName);
                userServices.updateUser(users);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("OK");

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "editpassword")
    public String EditPassword(@RequestParam(name = "password")String password,
                               @RequestParam(name = "repassword")String repass){
        Users users = getUser();
        if(password.equals(repass)){
            users.setPassword(passwordEncoder.encode(password));
            userServices.updateUser(users);
            return "redirect:/editmyprofile?success";
        }else{
            return "redirect:/editmyprofile?error";
        }
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/addpost")
//    public String addPost(@RequestParam(name = "textPost")String textPost,
//                          @RequestParam(name = "forUserId")Long forUserId
//    ) {
//        Users user = getUser();
//        Post post = new Post();
//        post.setPostText(textPost);
//        post.setCountLike(0);
//        post.setCountComment(0);
//        post.setDate(new Timestamp(System.currentTimeMillis()));
//        post.setForUsers(forUserId);
//        post.setUser(user);
//
//        postServices.addPost(post);
//        return "redirect:/mypage";
//    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/addcomment")
//    public String addComment(@RequestParam(name = "postId")Long postId,
//                             @RequestParam(name = "textComment")String textComment){
//        Users user = getUser();
//        Post post = postServices.getPost(postId);
//        Comment comment = new Comment();
//        comment.setComment(textComment);
//        comment.setDate(new Timestamp(System.currentTimeMillis()));
//        comment.setUser(user);
//        comment.setPost(post);
//        //post.setComment(comment);
//        commentsServices.addComment(comment);
//        Long countComment = commentsServices.countAllCommentsByPost(postId);
//        post.setCountComment(countComment.intValue());
//        postServices.savePost(post);
//        return "redirect:/mypage";
//    }


//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/deletePost")
//    public String deletePost(@RequestParam(name = "postId")Long postId){
//        Post post = postServices.getPost(postId);
//       // post.setComment(null);
//        postServices.savePost(post);
//        likePostServices.deleteAllLikesByPost(post);
//        commentsServices.delAllCommentsByPostId(postId);
//        postServices.deletePost(postId);
//        return "redirect:/mypage";
//    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deleteComment")
    public String deleteComment(@RequestParam(name = "commentId")Long commentId,
                                @RequestParam(name = "postId")Long postId){
        Post post = postServices.getPost(postId);
        Comment comment = commentsServices.getComment(commentId);
        //Comment commentPost = post.getComment();
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
//                return "redirect:/mypage";
//            }else{
//
//                post.setComment(null);
//                comment.setPost(null);
//                postServices.savePost(post);
//                commentsServices.saveComment(comment);
//                commentsServices.delComm(comment);
//                return "redirect:/mypage";
//            }
//
//        }else{
            commentsServices.delComm(comment);
        Long countComment = commentsServices.countAllCommentsByPost(postId);
        post.setCountComment(countComment.intValue());
        postServices.savePost(post);
            return "redirect:/mypage";
        //}

    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/likePost")
//    public String likePost(@RequestParam(name = "postId")Long postId,
//                           @RequestParam(name = "userId")Long userId){
//        Post post = postServices.getPost(postId);
//        Users user = userServices.getUserById(userId);
//        Long countLike = likePostServices.getCountLike(post);
//       LikePost likePost = likePostServices.getLikePost(post,user);
//        if(likePost!=null){
//            likePostServices.deleteLikePost(likePost);
//            countLike = likePostServices.getCountLike(post);
//            post.setCountLike(countLike.intValue());
//            postServices.savePost(post);
//        }else{
//            LikePost likePost1 = new LikePost();
//            likePost1.setPost(post);
//            likePost1.setUser(user);
//            likePostServices.addLikePost(likePost1);
//            countLike = likePostServices.getCountLike(post);
//            post.setCountLike(countLike.intValue());
//            postServices.savePost(post);
//        }
//
//        return "redirect:/mypage";
//    }




    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }
}
