package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class UserRoleResponseDto {

  private Long id;
  private Long userId;
  private Long roleId;

  private RoleResponseDto role;
}
