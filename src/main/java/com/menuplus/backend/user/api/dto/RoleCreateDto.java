package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.enumeration.SystemRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class RoleCreateDto {

    @NotNull
    private SystemRole name;

    private String description;
    private GeneralStatus status;
    private String hexColor;

    private List<@Valid RolePermissionCreateDto> rolePermissions;
    private List<@Valid RoleScreenCreateDto> roleScreens;
}
