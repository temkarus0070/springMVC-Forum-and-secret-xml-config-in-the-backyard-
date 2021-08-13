package org.temkarus0070.MvcApp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private String password;
    private String username;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority=new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        };
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
