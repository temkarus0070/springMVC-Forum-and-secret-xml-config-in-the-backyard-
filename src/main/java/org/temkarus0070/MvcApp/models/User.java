package org.temkarus0070.MvcApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.temkarus0070.MvcApp.converters.GrantedAuthorityToStringConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Users",schema = "public")
public class User implements Serializable {
    private static final long serialVersionUID = 8L;

public User(){}

    public User(MyUserDetails myUserDetails){
    this.username=myUserDetails.getUsername();
    this.password=myUserDetails.getPassword();
    this.setEnabled(true);
    this.setAccountNonLocked(true);
    this.setCredentialNonExpired(true);
    this.setAccountNonExpired(true);
    }

    @Column
    private String password;

    @Id
    private String username;


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> postSet) {
        this.posts = postSet;
    }


    @JsonIgnore()
    @Convert(converter = GrantedAuthorityToStringConverter.class)
    private List<GrantedAuthority> authorities;


    @Column
    private boolean accountNonExpired;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialNonExpired;

    @Column
    private boolean enabled;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialNonExpired() {
        return credentialNonExpired;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
