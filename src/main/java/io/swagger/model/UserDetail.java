package io.swagger.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class UserDetail implements UserDetails {

    private String userName;

    private User user;

    private String password;

    private Collection<SimpleGrantedAuthority> authorities;

    private boolean active;

    public UserDetail(String userName) {
        this.userName = userName;
    }

    public UserDetail() {
    }

    public UserDetail(User user) {
        this.user = user;
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();
        if (user.isIsCustomer())
            authorities.add(new SimpleGrantedAuthority("USER"));
        if (user.isIsEmployee())
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        this.active = user.isIsActive();
    }

    public User getUser(){
        return user;
    }

    // Returns the authorisation a user has
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
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
