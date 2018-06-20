package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Authority;
import com.ftn.djole.spring.repository.AuthorityReposittory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService implements AuthorityServiceInterface{

    @Autowired
    AuthorityReposittory authorityReposittory;
    @Override
    public Authority findByName(String name) {
        return authorityReposittory.findByName(name);
    }
}
