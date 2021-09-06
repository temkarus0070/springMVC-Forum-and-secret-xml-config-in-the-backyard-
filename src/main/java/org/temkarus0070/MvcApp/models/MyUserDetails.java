package org.temkarus0070.MvcApp.models;

import org.hibernate.annotations.Type;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails, Serializable {



    private String password;


    private String username;


    private Collection<GrantedAuthority> authorities=new LinkedList<>();


    private boolean accountNonExpired;


    private boolean accountNonLocked;


    private boolean credentialNonExpired;


    private boolean enabled;

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public MyUserDetails(){}

    public MyUserDetails(String username, String password, Collection<GrantedAuthority> authorities, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialNonExpired){
        List<GrantedAuthority> grantedAuthorities= new ArrayList<>(authorities);
        this.username=username;
        this.password=password;
        this.authorities.addAll(grantedAuthorities);
        this.enabled=enabled;
        this.accountNonExpired=accountNonExpired;
        this.accountNonLocked=accountNonLocked;
        this.credentialNonExpired=credentialNonExpired;
    }


   UserDetails  getUser(){
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
