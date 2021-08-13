package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.PostDao;
import org.temkarus0070.MvcApp.models.Post;

@Controller
public class PostsController {
    PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("Posts",postDao.index());
    return "posts/index";
    }

    @GetMapping("/posts/new")
    public String add(@ModelAttribute Post post){
        return "posts/new";
    }

    @PostMapping("/posts/new")
    public String create(@RequestParam Post post){
        postDao.add(post);
        return "redirect:/posts/index";
    }
}
