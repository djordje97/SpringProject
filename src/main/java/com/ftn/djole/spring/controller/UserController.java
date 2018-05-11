package com.ftn.djole.spring.controller;

import com.ftn.djole.spring.dto.UserDTO;
import com.ftn.djole.spring.entity.User;
import com.ftn.djole.spring.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserServiceIterface userServiceIterface;

    @GetMapping
    public ResponseEntity<List<UserDTO>>getUsers(){
        List<User>users=userServiceIterface.findAll();
        List<UserDTO>userDTOS=new ArrayList<>();
        for (User u: users) {
            userDTOS.add(new UserDTO(u));
        }
      return  new ResponseEntity<List<UserDTO>>(userDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO>getUser(@PathVariable("id") Integer id){
        User user=userServiceIterface.findOne(id);
        if(user ==null)
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO>saveUser(@RequestBody UserDTO userDTO){
        User user=new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoto(userDTO.getPhoto());
        user=userServiceIterface.save(user);
        return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<UserDTO>editUser(@RequestBody UserDTO userDTO,@PathVariable("id") Integer id){
        User user=userServiceIterface.findOne(id);
        if(user == null)
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhoto(userDTO.getPhoto());

        user=userServiceIterface.save(user);

        return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable("id")Integer id){
        User user=userServiceIterface.findOne(id);
        if(user != null)
        {
            userServiceIterface.remove(id);
            return  new ResponseEntity<Void>(HttpStatus.OK);

        }
        return  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
}
