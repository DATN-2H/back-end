package com.menuplus.backend.user.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleCreateDto {

  private Long id;
  private Long userId;

  @NotNull
  private Long roleId;
}
