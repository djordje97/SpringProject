package com.ftn.djole.spring.controller;

import com.ftn.djole.spring.entity.User;
import com.ftn.djole.spring.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserServiceIterface userServiceIterface;

    @GetMapping
    public ResponseEntity<List<User>>getUsers(){
        List<User>users=userServiceIterface.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
