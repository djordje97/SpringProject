package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByPost_Id(Integer postId);

    List<Comment> findAllByPost_IdOrderByDateDesc(Integer id);
    List<Comment> findAllByPost_IdOrderByLikesDesc(Integer id);
    List<Comment> findAllByPost_IdOrderByDislikeDesc(Integer id);

}
