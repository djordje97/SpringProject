package com.ftn.djole.spring.controller;

import com.ftn.djole.spring.dto.UserDTO;
import com.ftn.djole.spring.entity.Authority;
import com.ftn.djole.spring.entity.User;
import com.ftn.djole.spring.service.AuthorityServiceInterface;
import com.ftn.djole.spring.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserServiceIterface userServiceIterface;

    @Autowired
    AuthorityServiceInterface authorityServiceInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @RequestMapping(value ="/logged" )
    @PreAuthorize("hasRole('USER') or  hasRole('COMMENTATOR') or hasRole('ADMIN')")

    public ResponseEntity<UserDTO> user(Principal user){
        User userL=userServiceIterface.findByUsername(user.getName());
        return new ResponseEntity<>(new UserDTO(userL),HttpStatus.OK);
    }

    @GetMapping(value = "/get/role/{username}")
    public ResponseEntity<Authority>getRole(@PathVariable String username){
        User user=userServiceIterface.findByUsername(username);
        String role=user.getUserAuthority().iterator().next().getName();
        Authority authority=authorityServiceInterface.findByName(role);
        return new ResponseEntity<Authority>(authority,HttpStatus.OK);
    }


    @GetMapping(value = "/allUsername")
    public ResponseEntity<List<String>> allUsernames(){
        List<User> allUsers=userServiceIterface.findAll();
        List<String>usernames=new ArrayList<>();
        for (User u : allUsers) {
            usernames.add(u.getUsername());
        }

        return new ResponseEntity<List<String>>(usernames,HttpStatus.OK);
    }

    @PutMapping(value = "/role/{username}/{roleName}")
    ResponseEntity<Boolean> addRole(@PathVariable String username,@PathVariable String roleName){
        User u=userServiceIterface.findByUsername(username);
        Authority authority=authorityServiceInterface.findByName(roleName);

        u.getUserAuthority().add(authority);
        userServiceIterface.save(u);

        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping(value = "/get/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username){
        User user=userServiceIterface.findByUsername(username);
        if(user ==null)
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO>saveUser(@RequestBody UserDTO userDTO){
        User user=new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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
