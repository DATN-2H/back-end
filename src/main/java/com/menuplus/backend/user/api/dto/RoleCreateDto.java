package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.enumeration.GeneralStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

@Data
public class RoleCreateDto {

  @NotEmpty
  private String name;

  private String description;
  private GeneralStatus status;

  private List<@Valid RolePermissionCreateDto> rolePermissions;
  private List<@Valid RoleScreenCreateDto> roleScreens;
}
