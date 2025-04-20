package com.menuplus.backend.library.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserDetailsCustom implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsCustom(UserClaims userClaims) {
        this.authorities = new ArrayList<>();
        this.userId = userClaims.getUserId();
        this.username = userClaims.getEmail();
    }

    public static UserDetailsCustom getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!authentication.isAuthenticated()) {
                return null;
            }

            return (UserDetailsCustom) authentication.getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

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
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}