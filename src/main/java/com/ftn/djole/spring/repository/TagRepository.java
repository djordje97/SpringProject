package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {

    List<Tag> findByPosts_Id(Integer postId);
}
