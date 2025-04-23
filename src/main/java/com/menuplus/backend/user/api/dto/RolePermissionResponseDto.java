package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionResponseDto extends BaseResponseDto {

  private Long id;
  private Long roleId;
  private Long permissionId;
  private Boolean isLimitedByOwner;
  private String limitedIp;

  private PermissionResponseDto permission;
}
