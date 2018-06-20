package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityReposittory extends JpaRepository<Authority,Integer> {

    Authority findByName(String name);
}
