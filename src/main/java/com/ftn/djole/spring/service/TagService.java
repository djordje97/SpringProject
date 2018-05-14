package com.ftn.djole.spring.service;

import com.ftn.djole.spring.entity.Tag;
import com.ftn.djole.spring.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements TagServiceInterfce {

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag findOne(Integer tagsId) {
        return tagRepository.getOne(tagsId);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag save(Tag tags) {
        return tagRepository.save(tags);
    }

    @Override
    public void remove(Integer id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> findByPosts_Id(Integer postId) {
        return tagRepository.findByPosts_Id(postId);
    }
}
