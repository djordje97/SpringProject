package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Post;
import com.ftn.djole.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService implements PostServiceInterface {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post findOne(Integer postId) {
        return postRepository.getOne(postId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post posts) {
        return postRepository.save(posts);
    }

    @Override
    public void remove(Integer id) {
        postRepository.deleteById(id);
    }
}
