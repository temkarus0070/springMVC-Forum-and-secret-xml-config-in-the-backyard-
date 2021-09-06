package org.temkarus0070.MvcApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Posts")
public class Post implements Serializable {


    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column
    private String header;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @JsonIgnoreProperties({"posts","comments"})
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String text;

    @JsonIgnoreProperties("posts")
    @ManyToOne()
    @JoinColumn(name = "section_id")
    private Section section;

    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Post() {

    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public Post(int id, User user, String text, Section section) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.section = section;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User authorId) {
        this.user = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section sectionId) {
        this.section = sectionId;
    }
}
