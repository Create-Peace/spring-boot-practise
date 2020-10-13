package com.ming.practise.common.security;

import com.ming.practise.common.entity.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class JwtUser implements UserDetails {

    private String username;

    private String password;

    private Set<? extends GrantedAuthority> authorities;

    private AdminUser adminUser;

    public JwtUser(String username, String password, Set<? extends GrantedAuthority> authorities,AdminUser adminUser) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.adminUser= adminUser;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

