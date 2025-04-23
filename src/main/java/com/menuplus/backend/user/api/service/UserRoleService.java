package com.menuplus.backend.user.api.service;

import com.menuplus.backend.user.api.dto.UserRoleCreateDto;
import com.menuplus.backend.user.api.dto.UserRoleResponseDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleService {
  List<UserRoleResponseDto> createMany(List<UserRoleCreateDto> createDtos);

  List<UserRoleResponseDto> upsert(
    Long userId,
    List<UserRoleCreateDto> updateDtos
  );

  void deleteByUserId(Long userId);
}
