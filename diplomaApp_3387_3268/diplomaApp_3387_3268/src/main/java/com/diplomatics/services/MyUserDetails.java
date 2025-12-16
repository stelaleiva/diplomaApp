package com.diplomatics.services;

import com.diplomatics.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;

    private String full_name;

    private boolean active;

    private User user;
    public MyUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isEnabled();
        this.user=user;
        this.full_name=user.getFull_name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    public String getFull_Name() {
        return full_name;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return userName; }

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
        return active;
    }

}
