package com.ftn.djole.spring.controller;


import com.ftn.djole.spring.dto.CommentDTO;
import com.ftn.djole.spring.dto.PostDTO;
import com.ftn.djole.spring.entity.Comment;
import com.ftn.djole.spring.entity.Post;
import com.ftn.djole.spring.service.CommentServiceInterface;
import com.ftn.djole.spring.service.PostServiceInterface;
import com.ftn.djole.spring.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/comments")
public class CommentController {


    @Autowired
    private CommentServiceInterface commentServiceInterface;

    @Autowired
    private UserServiceIterface userServiceIterface;

    @Autowired
    private PostServiceInterface postServiceInterface;


    @GetMapping
    public ResponseEntity<List<CommentDTO>>getComments(){
        List<Comment> comments=commentServiceInterface.findAll();
        List<CommentDTO>commentDTOS=new ArrayList<>();
        for (Comment comment:comments) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO>getComment(@PathVariable("id") Integer id){
        Comment comment=commentServiceInterface.findOne(id);
        if(comment == null)
            return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);

        return  new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.OK);
    }


    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<CommentDTO>>getCommentsByPost(@PathVariable("id")Integer id){
        List<Comment> comments=commentServiceInterface.findByPost_Id(id);
        List<CommentDTO>commentDTOS=new ArrayList<>();
        for (Comment comment:comments) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/post/order/{id}/{orderBy}")
    public ResponseEntity<List<CommentDTO>>getCommentsByPostOrdered(@PathVariable("id")Integer id,@PathVariable("orderBy") String orderBy){
        List<Comment> comments=null;
        if(orderBy.equals("date")){
            comments=commentServiceInterface.findAllByPost_IdOrderByDate(id);
        }else if(orderBy.equals("likes")){
            comments=commentServiceInterface.findAllByPost_IdOrderByLike(id);
        }
        else{
            comments=commentServiceInterface.findAllByPost_IdOrderByDislike(id);
        }
        List<CommentDTO>commentDTOS=new ArrayList<>();
        for (Comment comment:comments) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOS,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommentDTO> addPost(@RequestBody CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setTitle(commentDTO.getTitle());
        comment.setDescription(commentDTO.getDescription());
        Date now=new Date();
        comment.setDate(now);
        comment.setLikes(commentDTO.getLikes());
        comment.setDislike(commentDTO.getDislikes());
        comment.setUser(userServiceIterface.findOne(commentDTO.getUser().getId()));
        comment.setPost(postServiceInterface.findOne(commentDTO.getPost().getId()));

        comment=commentServiceInterface.save(comment);
        return new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<CommentDTO>updateComment(@PathVariable("id")Integer id,@RequestBody CommentDTO commentDTO){
        Comment comment=commentServiceInterface.findOne(id);
        if(comment == null)
            return  new ResponseEntity<CommentDTO>(HttpStatus.BAD_REQUEST);
        comment.setTitle(commentDTO.getTitle());
        comment.setDescription(commentDTO.getDescription());
        comment.setLikes(commentDTO.getLikes());
        comment.setDislike(commentDTO.getDislikes());

        comment=commentServiceInterface.save(comment);
        return new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id){

        Comment comment=commentServiceInterface.findOne(id);
        if(comment == null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        commentServiceInterface.remove(comment.getId());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
