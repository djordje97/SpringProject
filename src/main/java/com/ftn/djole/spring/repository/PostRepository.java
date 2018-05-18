package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByTags_Id(Integer tagId);
}
