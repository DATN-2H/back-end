package com.menuplus.backend.user.api.service;

import com.menuplus.backend.library.common.PageResponse;
import com.menuplus.backend.user.api.dto.RoleCreateDto;
import com.menuplus.backend.user.api.dto.RoleListRequest;
import com.menuplus.backend.user.api.dto.RoleResponseDto;
import com.menuplus.backend.user.api.dto.RoleUpdateDto;
import java.util.List;

public interface RoleService {
  RoleResponseDto create(RoleCreateDto createDto);

  RoleResponseDto update(Long id, RoleUpdateDto updateDto);

  void delete(Long id);

  RoleResponseDto detail(Long id);

  List<RoleResponseDto> list();
}
