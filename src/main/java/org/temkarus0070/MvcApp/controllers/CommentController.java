package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.Repositories.CommentRepository;
import org.temkarus0070.MvcApp.dao.Repositories.UserRepository;
import org.temkarus0070.MvcApp.models.Comment;
import org.temkarus0070.MvcApp.models.User;

import java.security.Principal;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {
    UserRepository userRepository;
    CommentRepository commentRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping()
    public void create(@RequestBody Comment comment, Principal principal){
        User user=userRepository.findById(principal.getName()).get();
        if(user!=null) {
            comment.setUser(user);
            commentRepository.save(comment);
        }
    }

    @DeleteMapping()
    public void delete(@RequestParam(name = "commentId") long commentId,Principal principal){
        User user=userRepository.findById(principal.getName()).get();
        GrantedAuthority grantedAuthority=new GrantedAuthority("admin");

        GrantedAuthority grantedAuthority1=new GrantedAuthority("admin");
        boolean f=grantedAuthority.equals(grantedAuthority1);
        if(user!=null) {
            Comment comment = commentRepository.findById(commentId).get();
            if (comment!=null && user.getAuthorities().contains(new GrantedAuthority("admin"))) {
                this.commentRepository.delete(comment);
            }
            else if(comment!=null){
                if(user.getUsername().equals(comment.getUser().getUsername())){
                    this.commentRepository.delete(comment);
                }
            }

        }
    }
}
