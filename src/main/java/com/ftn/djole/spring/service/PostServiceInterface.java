package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Post;

import java.util.List;

public interface PostServiceInterface {

    Post findOne(Integer postId);

    List<Post> findAll();

    List<Post> findByTags_Id(Integer tagId);

    Post save(Post posts);

    void remove(Integer id);

    List<Post> findAllByOrOrderByDate();
    List<Post> findAllByOrderByLikes();
    List<Post> findAllByOrderByDislike();
    List<Post> findAllOrOrderByCommentsCount();

    List<Post> findAllBySearch(String text);
}
