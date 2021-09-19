package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.LikePost;
import com.example.socialnetworkspring.models.Post;
import com.example.socialnetworkspring.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LikePostRepository extends JpaRepository<LikePost,Long> {
    Long countAllByPost(Post post);
    LikePost findByPostAndUser(Post post , Users user);
    List<LikePost> findAllByPost(Post post);
    void deleteAllByPost(Post post);
}
