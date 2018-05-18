package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Comment;

import java.util.List;

public interface CommentServiceInterface {

    Comment findOne(Integer commentId);

    List<Comment> findAll();

    Comment save(Comment comments);

    void remove(Integer id);

    List<Comment> findByPost_Id(Integer postId);
}
