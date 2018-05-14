package com.ftn.djole.spring.controller;


import com.ftn.djole.spring.dto.CommentDTO;
import com.ftn.djole.spring.entity.Comment;
import com.ftn.djole.spring.service.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/comments")
public class CommentController {


    @Autowired
    private CommentServiceInterface commentServiceInterface;


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



}
