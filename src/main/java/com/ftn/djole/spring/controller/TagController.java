package com.ftn.djole.spring.controller;

import com.ftn.djole.spring.dto.PostDTO;
import com.ftn.djole.spring.dto.TagDTO;
import com.ftn.djole.spring.entity.Post;
import com.ftn.djole.spring.entity.Tag;
import com.ftn.djole.spring.service.PostServiceInterface;
import com.ftn.djole.spring.service.TagServiceInterfce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/tags")
public class TagController {

    @Autowired
    private TagServiceInterfce tagServiceInterfce;

    @Autowired
    PostServiceInterface postServiceInterface;

    @GetMapping
    public ResponseEntity<List<TagDTO>>getTags(){

        List<Tag>tags=tagServiceInterfce.findAll();

        List<TagDTO>tagDTOS=new ArrayList<>();

        for (Tag tag:tags)
            tagDTOS.add(new TagDTO(tag));

        return new ResponseEntity<List<TagDTO>>(tagDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TagDTO>getTag(@PathVariable("id") Integer id){
        Tag tag=tagServiceInterfce.findOne(id);
        if(tag == null)
            return  new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<TagDTO>(new TagDTO(tag),HttpStatus.OK);
    }

    @GetMapping(value = "post/{id}")
    public ResponseEntity<List<PostDTO>>getPostsByTag(@PathVariable("id") Integer id){
        List<Post> posts=postServiceInterface.findByTags_Id(id);
        List<PostDTO>postDTOS=new ArrayList<>();
        if(posts == null)
            return  new ResponseEntity<List<PostDTO>>(HttpStatus.NOT_FOUND);
        for(Post post:posts)
            postDTOS.add(new PostDTO(post));
        return  new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TagDTO>addTag(@RequestBody TagDTO tagDTO){
        Tag tag=new Tag();
        tag.setName(tagDTO.getName());

        tag=tagServiceInterfce.save(tag);
        return new ResponseEntity<TagDTO>(new TagDTO(tag),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDTO,@PathVariable("id") Integer id){
        Tag tag=tagServiceInterfce.findOne(id);
        if(tag == null)
            return new ResponseEntity<TagDTO>(HttpStatus.BAD_REQUEST);
        tag.setName(tagDTO.getName());
        tag=tagServiceInterfce.save(tag);
        return new ResponseEntity<>(new TagDTO(tag),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteTag(@PathVariable("id") Integer id){
        Tag tag=tagServiceInterfce.findOne(id);
        if(tag == null)
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        tagServiceInterfce.remove(tag.getId());
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }
}
