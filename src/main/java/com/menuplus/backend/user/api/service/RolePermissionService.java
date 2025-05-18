package com.menuplus.backend.user.api.service;

import com.menuplus.backend.user.api.dto.RolePermissionCreateDto;
import com.menuplus.backend.user.api.dto.RolePermissionResponseDto;
import java.util.List;

public interface RolePermissionService {
    List<RolePermissionResponseDto> createMany(
        List<RolePermissionCreateDto> createDtos
    );

    List<RolePermissionResponseDto> upsert(
        Long roleId,
        List<RolePermissionCreateDto> updateDtos
    );

    void deleteByRoleId(Long roleId);
}
