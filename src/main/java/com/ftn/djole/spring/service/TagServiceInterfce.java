package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Tag;

import java.util.List;

public interface TagServiceInterfce {
    Tag findOne(Integer tagsId);

    List<Tag> findAll();

    Tag save(Tag tags);

    void remove(Integer id);
}
