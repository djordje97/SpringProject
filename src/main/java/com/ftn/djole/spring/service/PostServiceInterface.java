package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Post;

import java.util.List;

public interface PostServiceInterface {

    Post findOne(Integer postId);

    List<Post> findAll();

    Post save(Post posts);

    void remove(Integer id);
}
