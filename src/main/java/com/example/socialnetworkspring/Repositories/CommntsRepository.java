package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Comment;
import com.example.socialnetworkspring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommntsRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostIdOrderByIdDesc(Long id);
    Long countAllByPostId(Long id);
    Comment findFirstByPostId(Long id);
    void deleteAllByPostId(Long id);
}
