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

    @Override
    public List<Comment> findByPost_Id(Integer postId) {
        return commentRepository.findByPost_Id(postId);
    }

    @Override
    public List<Comment> findAllByPost_IdOrderByDate(Integer id) {
        return commentRepository.findAllByPost_IdOrderByDateDesc(id);
    }

    @Override
    public List<Comment> findAllByPost_IdOrderByLike(Integer id) {
        return commentRepository.findAllByPost_IdOrderByLikesDesc(id);
    }

    @Override
    public List<Comment> findAllByPost_IdOrderByDislike(Integer id) {
        return commentRepository.findAllByPost_IdOrderByDislikeDesc(id);
    }
}
