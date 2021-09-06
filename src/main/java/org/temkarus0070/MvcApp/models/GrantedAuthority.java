package org.temkarus0070.MvcApp.models;

import java.util.Objects;

public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {

    private String authority;
    public GrantedAuthority(String role){
        this.authority=role;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantedAuthority that = (GrantedAuthority) o;
        return that.authority.equals(this.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
