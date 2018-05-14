package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.User;
import com.ftn.djole.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceIterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOne(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User users) {
        return userRepository.save(users);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }
}
