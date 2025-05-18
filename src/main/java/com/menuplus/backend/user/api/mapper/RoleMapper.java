package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.MapperHelper;
import com.menuplus.backend.user.api.dto.RoleCreateDto;
import com.menuplus.backend.user.api.dto.RoleResponseDto;
import com.menuplus.backend.user.model.Role;
import com.menuplus.backend.user.model.Role_;
import java.util.ArrayList;
import java.util.List;

public class RoleMapper {

    public static List<String> ListExcludeFields = List.of(
        Role_.ROLE_PERMISSIONS,
        Role_.ROLE_SCREENS
    );

    public static Role createEntity(RoleCreateDto createDto) {
        Role entity = new Role();
        MapUtil.copyCreateProperties(createDto, entity);
        return entity;
    }

    public static RoleResponseDto createResponse(Role entity) {
        return createResponse(entity, List.of());
    }

    public static RoleResponseDto createResponse(
        Role entity,
        List<String> excludeFields
    ) {
        RoleResponseDto responseDto = new RoleResponseDto();
        MapUtil.copyResponseProperties(entity, responseDto);
        if (
            !excludeFields.contains(Role_.ROLE_PERMISSIONS) &&
            entity.getRolePermissions() != null
        ) {
            responseDto.setRolePermissions(
                MapperHelper.map(
                    new ArrayList<>(entity.getRolePermissions()),
                    RolePermissionMapper::createResponse
                )
            );
        }
        if (
            !excludeFields.contains(Role_.ROLE_SCREENS) &&
            entity.getRoleScreens() != null
        ) {
            responseDto.setRoleScreens(
                MapperHelper.map(
                    new ArrayList<>(entity.getRoleScreens()),
                    RoleScreenMapper::createResponse
                )
            );
        }
        return responseDto;
    }
}
