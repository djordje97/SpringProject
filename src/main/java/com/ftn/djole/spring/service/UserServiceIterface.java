package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.User;

import java.util.List;

public interface UserServiceIterface {

    User findOne(Integer userId);

    List<User> findAll();

    User save(User users);

    void remove(Integer id);
}
