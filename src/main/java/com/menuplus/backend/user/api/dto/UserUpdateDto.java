package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.enumeration.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class UserUpdateDto {

  @NotNull(message = "{account.email.null}")
  private String email;

  private String username;
  private String fullName;
  private LocalDate birthdate;
  private Gender gender;
  private String phoneNumber;
  private Boolean isFullRole;

  private List<@Valid UserRoleCreateDto> userRoles;

  public void setEmail(String email) {
    this.email = email.toLowerCase();
  }

  public void setUsername(String username) {
    this.username = username.toLowerCase();
  }
}
