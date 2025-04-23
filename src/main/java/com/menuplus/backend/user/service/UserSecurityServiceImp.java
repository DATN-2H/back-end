package com.menuplus.backend.user.service;

import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.security.UserDetailsCustom;
import com.menuplus.backend.user.api.service.UserSecurityService;
import com.menuplus.backend.user.model.User;
import com.menuplus.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityServiceImp implements UserSecurityService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    User user = userRepository.findByEmailAndStatusIsNot(
      email,
      GeneralStatus.DELETED
    );

    if (user == null) {
      throw new UsernameNotFoundException(
        "User not found with email : " + email
      );
    }
    return UserDetailsCustom.builder()
      .userId(user.getId())
      .username(user.getEmail())
      .password(user.getPassword())
      .build();
  }
}
