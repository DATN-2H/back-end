package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResponseDto extends BaseResponseDto {

  private Long id;
  private String name;
  private String description;
  private List<RolePermissionResponseDto> rolePermissions;
  private List<RoleScreenResponseDto> roleScreens;
}
