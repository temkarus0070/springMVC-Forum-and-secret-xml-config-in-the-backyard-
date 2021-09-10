package org.temkarus0070.MvcApp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Sections",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Section implements Serializable {
    private static final long serialVersionUID = 9L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;



    @NotNull
    @Column
    private String name;

    @JsonIgnoreProperties("section")
    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
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
