package org.temkarus0070.MvcApp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Sections")
public class Section implements Serializable {
    private static final long serialVersionUID = 9L;
    @Id
    private int id;



    @Column
    private String name;

    @JsonIgnoreProperties("section")
    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY)
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> postList) {
        this.posts = postList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section(){}
}
