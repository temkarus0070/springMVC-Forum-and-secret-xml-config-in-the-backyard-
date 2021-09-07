package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.Exceptions.PostNotFoundException;
import org.temkarus0070.MvcApp.dao.PostRepository;
import org.temkarus0070.MvcApp.dao.SectionRepository;
import org.temkarus0070.MvcApp.dao.UserRepository;
import org.temkarus0070.MvcApp.models.Post;
import org.temkarus0070.MvcApp.models.Section;
import org.temkarus0070.MvcApp.models.User;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PostsController {
    UserRepository userRepository;
    PostRepository postRepository;
    SectionRepository sectionRepository;


    @Autowired
    public void setSectionRepository(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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




    @PostMapping(value = "/posts/new",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Post post,Principal principal){
        Post post1=new Post();
           User user= userRepository.findById(principal.getName()).get();
           Section section=sectionRepository.findById(post.getSection().getId()).get();
           if(user!=null) {
                post.setUser(user);
               post.setSection(section);
               postRepository.save(post);
           }
    }
}
