package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Authority;
import com.ftn.djole.spring.repository.AuthorityReposittory;

public interface AuthorityServiceInterface  {
    Authority findByName(String name);
}
