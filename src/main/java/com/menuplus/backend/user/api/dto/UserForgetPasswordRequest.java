package com.menuplus.backend.user.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserForgetPasswordRequest {

  @NotNull(message = "{account.email.null}")
  private String email;

  public void setEmail(String email) {
    this.email = email != null ? email.toLowerCase() : null;
  }
}
