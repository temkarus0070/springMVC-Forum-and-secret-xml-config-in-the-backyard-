package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.Repositories.PostRepository;


@RequestMapping("/stats")
@RestController()
public class StatsController {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping(path = "/postsCountByUser")
    public Long getPostsCountByUser(@RequestParam String username){
        return postRepository.countPostsByUserUsername(username);
    }


}
