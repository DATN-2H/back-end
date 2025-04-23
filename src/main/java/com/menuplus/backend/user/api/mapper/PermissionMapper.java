package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.PermissionResponseDto;
import com.menuplus.backend.user.model.Permission;

public class PermissionMapper {

  public static PermissionResponseDto createResponse(Permission entity) {
    PermissionResponseDto responseDto = new PermissionResponseDto();
    MapUtil.copyResponseProperties(entity, responseDto);
    return responseDto;
  }
}
