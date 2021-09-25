package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.Exceptions.PostNotFoundException;
import org.temkarus0070.MvcApp.dao.Repositories.PostRepository;
import org.temkarus0070.MvcApp.dao.Repositories.SectionRepository;
import org.temkarus0070.MvcApp.dao.Repositories.UserRepository;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.temkarus0070.MvcApp.models.Post;
import org.temkarus0070.MvcApp.models.Section;
import org.temkarus0070.MvcApp.models.User;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/post")
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

    @GetMapping()
    public List<Post> index(){
        List<Post> postList= postRepository.findAll();
       return postList;
    }

    @GetMapping(path = "/getPostsBySection")
    public List<Post> getPostsBySection(@RequestParam int sectionId) {
        return postRepository.findAllBySectionId(sectionId);
    }
    @GetMapping(path = "/{postId}")
    public Post showPost(@PathVariable("postId") int postId){
        Post post=postRepository.findById(postId).get();
        if(post==null)
            throw new PostNotFoundException();
        return post;
    }


    @PostMapping()
    public void create(@RequestBody Post post,Principal principal){
        if(post.getUser()==null) {
            User user = userRepository.findById(principal.getName()).get();
           Section section = sectionRepository.findById(post.getSection().getId()).get();
            post.setUser(user);
           post.setSection(section);
        }
        postRepository.save(post);
    }

    @PutMapping
    public void update(@RequestBody Post post,Principal principal){
            if(post.getUser()==null){

            User user = userRepository.findById(principal.getName()).get();

        }
        Section section=sectionRepository.findById(post.getSection().getId()).get();
        post.setSection(section);
        postRepository.save(post);
    }

    @DeleteMapping()
    public void delete(@RequestParam int postId,Principal principal){
        Post post=postRepository.getById(postId);
        if(post.getUser().getUsername().equals(principal.getName())) {
        postRepository.deleteById(postId);
        }
        else {
            User user=userRepository.getById(principal.getName());
            if(user!=null){
                GrantedAuthority admin=new GrantedAuthority("admin");
                if(user.getAuthorities().stream().filter(e->e.equals(admin)).findFirst().get()!=null){
                    postRepository.deleteById(postId);
                }
            }
        }
    }
}
