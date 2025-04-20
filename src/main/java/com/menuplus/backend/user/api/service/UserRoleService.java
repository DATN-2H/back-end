package com.menuplus.backend.user.api.service;

import com.menuplus.backend.user.api.dto.UserRoleCreateDto;
import com.menuplus.backend.user.api.dto.UserRoleResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleService {
    List<UserRoleResponseDto> createMany(List<UserRoleCreateDto> createDtos);

    List<UserRoleResponseDto> upsert(Long userId, List<UserRoleCreateDto> updateDtos);

    void deleteByUserId(Long userId);
}
