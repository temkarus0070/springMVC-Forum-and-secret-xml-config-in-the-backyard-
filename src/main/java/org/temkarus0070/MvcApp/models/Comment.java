package org.temkarus0070.MvcApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnoreProperties({"comments","posts"})
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties("comments")
    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long commentId) {
        this.id = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment(){

    }
}
