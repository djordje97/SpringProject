package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Comment;
import com.ftn.djole.spring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentServiceInterface{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment findOne(Integer commentId) {
        return commentRepository.getOne(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comments) {
        return commentRepository.save(comments);
    }

    @Override
    public void remove(Integer id) {
        commentRepository.deleteById(id);
    }
}
