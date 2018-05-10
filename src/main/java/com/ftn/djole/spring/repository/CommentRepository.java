package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
