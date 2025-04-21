package com.menuplus.backend.library.security;

import com.menuplus.backend.user.api.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userSecurityService.loadUserByUsername(email);
    }

    public UserDetails loadUserByUserClaims(UserClaims userClaims) {
        return new UserDetailsCustom(userClaims);
    }

}
