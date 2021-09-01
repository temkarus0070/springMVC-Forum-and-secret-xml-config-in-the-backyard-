package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.temkarus0070.MvcApp.Exceptions.PostNotFoundException;
import org.temkarus0070.MvcApp.dao.PostRepository;
import org.temkarus0070.MvcApp.models.Post;

import java.util.List;

@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostsController {
    PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/posts")
    public List<Post> index(){
        List<Post> postList= postRepository.findAll();
       return postList;
    }

    @GetMapping("/posts/{postId}")
    public Post showPost(@PathVariable("postId") int postId){
        Post post=postRepository.findById(postId).get();
        if(post==null)
            throw new PostNotFoundException();
        return post;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts/new")
    public Post create(@RequestBody Post post){
        postRepository.save(post);
        return post;
    }
}
