package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import com.menuplus.backend.library.enumeration.SystemRole;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResponseDto extends BaseResponseDto {

    private Long id;
    private SystemRole name;
    private String description;
    private String hexColor;
    private List<RolePermissionResponseDto> rolePermissions;
    private List<RoleScreenResponseDto> roleScreens;
}
