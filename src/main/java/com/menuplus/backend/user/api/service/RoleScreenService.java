package com.menuplus.backend.user.api.service;

import com.menuplus.backend.user.api.dto.RoleScreenCreateDto;
import com.menuplus.backend.user.api.dto.RoleScreenResponseDto;
import java.util.List;

public interface RoleScreenService {
    List<RoleScreenResponseDto> createMany(
        List<RoleScreenCreateDto> createDtos
    );

    List<RoleScreenResponseDto> upsert(
        Long roleId,
        List<RoleScreenCreateDto> updateDtos
    );

    void deleteByRoleId(Long roleId);
}
