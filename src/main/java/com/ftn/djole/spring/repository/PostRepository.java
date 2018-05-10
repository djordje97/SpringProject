package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
