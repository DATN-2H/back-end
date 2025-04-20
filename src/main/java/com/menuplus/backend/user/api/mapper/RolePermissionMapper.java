package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.RolePermissionCreateDto;
import com.menuplus.backend.user.api.dto.RolePermissionResponseDto;
import com.menuplus.backend.user.model.RolePermission;

public class RolePermissionMapper {
    public static RolePermission createEntity(RolePermissionCreateDto createDto) {
        RolePermission entity = new RolePermission();
        MapUtil.copyCreateProperties(createDto, entity);
        return entity;
    }

    public static RolePermissionResponseDto createResponse(RolePermission entity) {
        RolePermissionResponseDto responseDto = new RolePermissionResponseDto();
        MapUtil.copyResponseProperties(entity, responseDto);
        if (entity.getPermission() != null) {
            responseDto.setPermission(PermissionMapper.createResponse(entity.getPermission()));
        }
        return responseDto;
    }
}
