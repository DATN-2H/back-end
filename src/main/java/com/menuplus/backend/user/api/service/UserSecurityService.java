package com.menuplus.backend.user.api.service;

import com.menuplus.backend.library.security.UserClaims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSecurityService {
    UserDetails loadUserByUsername(String var1)
        throws UsernameNotFoundException;
}
