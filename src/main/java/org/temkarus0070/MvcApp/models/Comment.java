package org.temkarus0070.MvcApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @JsonIgnoreProperties({"comments","posts"})
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
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

    @NotNull
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
