package org.temkarus0070.MvcApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

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
    if(myUserDetails.getAuthorities()!=null){
        if(authorities==null)
            this.authorities=new ArrayList<GrantedAuthority>();
        this.authorities.addAll(myUserDetails.getAuthorities());
    }
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
    private List<Post> postSet;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public List<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(List<Post> postSet) {
        this.postSet = postSet;
    }

    @ElementCollection(fetch = FetchType.EAGER)
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
