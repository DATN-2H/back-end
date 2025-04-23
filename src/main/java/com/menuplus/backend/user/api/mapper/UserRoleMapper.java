package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.UserRoleCreateDto;
import com.menuplus.backend.user.api.dto.UserRoleResponseDto;
import com.menuplus.backend.user.model.UserRole;

public class UserRoleMapper {

  public static UserRole createEntity(UserRoleCreateDto createDto) {
    UserRole entity = new UserRole();
    MapUtil.copyCreateProperties(createDto, entity);
    return entity;
  }

  public static UserRoleResponseDto createResponse(UserRole entity) {
    UserRoleResponseDto responseDto = new UserRoleResponseDto();
    MapUtil.copyResponseProperties(entity, responseDto);
    if (entity.getRole() != null) {
      responseDto.setRole(RoleMapper.createResponse(entity.getRole()));
    }
    return responseDto;
  }
}
