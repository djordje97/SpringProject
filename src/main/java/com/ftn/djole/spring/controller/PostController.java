package com.ftn.djole.spring.controller;

import com.ftn.djole.spring.dto.PostDTO;
import com.ftn.djole.spring.dto.TagDTO;
import com.ftn.djole.spring.entity.Post;
import com.ftn.djole.spring.entity.Tag;
import com.ftn.djole.spring.service.PostServiceInterface;
import com.ftn.djole.spring.service.TagServiceInterfce;
import com.ftn.djole.spring.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {

    @Autowired
    private PostServiceInterface postServiceInterface;

    @Autowired
    private UserServiceIterface userServiceIterface;

    @Autowired
    TagServiceInterfce tagServiceInterfce;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts(){
        List<Post> posts=postServiceInterface.findAll();
        List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/order/{orderBy}")
        public ResponseEntity<List<PostDTO>> getPostsOrdered(@PathVariable("orderBy") String orderBy){
            List<Post> posts=null;
            if(orderBy.equals("date")){
                posts=postServiceInterface.findAllByOrOrderByDate();
            }else if(orderBy.equals("likes")){
                posts=postServiceInterface.findAllByOrderByLikes();
            }else if(orderBy.equals("dislikes")){
                posts=postServiceInterface.findAllByOrderByDislike();
            }else{
                posts=postServiceInterface.findAllOrOrderByCommentsCount();
            }
            List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }


    @GetMapping(value = "/search/{text}")
    public ResponseEntity<List<PostDTO>> getPostSearched(@PathVariable("text") String text){
        List<Post> posts=postServiceInterface.findAllBySearch(text);

        List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") Integer id){
        Post post=postServiceInterface.findOne(id);
        if(post == null)
            return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
    }

    @GetMapping(value = "/tag/{id}")
    public ResponseEntity<List<TagDTO>> getTagByPost(@PathVariable("id") Integer id){
        List<Tag> tags=tagServiceInterfce.findByPosts_Id(id);
        List<TagDTO>tagDTOS=new ArrayList<>();
        if(tags == null)
            return new ResponseEntity<List<TagDTO>>(HttpStatus.NOT_FOUND);
        else {

            for (Tag t:tags)
                tagDTOS.add(new TagDTO(t));
        }
        return new ResponseEntity<List<TagDTO>>(tagDTOS,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
        Post post=new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setLikes(postDTO.getLikes());
        post.setDislike(postDTO.getDislike());
        Date now=new Date();
        post.setDate(now);
        post.setLatitude(postDTO.getLatitude());
        post.setLongitude(postDTO.getLongitude());
        post.setPhoto(postDTO.getPhoto());
        post.setUser(userServiceIterface.findOne(postDTO.getUser().getId()));

        post=postServiceInterface.save(post);
        return  new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable("id") Integer id){

        Post post=postServiceInterface.findOne(id);
        if(post == null)
            return  new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setPhoto(postDTO.getPhoto());
        post.setLikes(postDTO.getLikes());
        post.setDislike(postDTO.getDislike());
        post.setDate(postDTO.getDate());
        post.setLongitude(postDTO.getLongitude());
        post.setLatitude(postDTO.getLatitude());
        post.setUser(userServiceIterface.findOne(postDTO.getUser().getId()));


        post=postServiceInterface.save(post);
        return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id")Integer id){
        Post post=postServiceInterface.findOne(id);
        if(post == null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        postServiceInterface.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
