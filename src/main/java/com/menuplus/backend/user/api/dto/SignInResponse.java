package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class SignInResponse {

  private String token;

  private UserDtoResponse account;
}
