package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.Repositories.PostRepository;
import org.temkarus0070.MvcApp.models.Section;


@RequestMapping("/stats")
@RestController()
public class StatsController {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping(path = "/postsCount")
    public Long getPostsCount(){
        return postRepository.countPosts();
    }


    @GetMapping(path = "/postsCountBySection")
    public Long getPostsCountBySection(@RequestParam int sectionId){
        return postRepository.countPostsBySectionId(sectionId);
    }
}
