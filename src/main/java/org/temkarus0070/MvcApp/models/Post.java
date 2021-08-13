package org.temkarus0070.MvcApp.models;

import java.sql.Date;

public class Post {
    private int id;
    private String authorId;
    private String text;
    private int sectionId;
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public Post(int id, String authorId, String text, int sectionId) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.sectionId = sectionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
