package com.menuplus.backend.user.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolePermissionCreateDto {

  private Long id;
  private Long roleId;

  @NotNull
  private Long permissionId;

  private Boolean isLimitedByOwner;
  private String limitedIp;
}
