package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.PostRepository;
import org.temkarus0070.MvcApp.models.Post;

import java.util.List;

@Controller
public class PostsController {
    PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String index(Model model){
        List<Post> postList= postRepository.findAll();
        model.addAttribute("Posts", postRepository.findAll());
    return "posts/index";
    }

    @GetMapping("/posts/{postId}")
    public String showPost(@PathVariable("postId") int postId,Model model){
        Post post=postRepository.findById(postId).get();
        model.addAttribute("post",post);
        return "posts/show";
    }

    @GetMapping("/posts/new")
    public String add(@ModelAttribute Post post, BindingResult bindingResult){
        return "posts/new";
    }

    @PostMapping("/posts/new")
    public String create(@RequestParam Post post){
        postRepository.save(post);
        return "redirect:/posts/index";
    }
}
