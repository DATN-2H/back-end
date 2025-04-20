package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.RoleScreenCreateDto;
import com.menuplus.backend.user.api.dto.RoleScreenResponseDto;
import com.menuplus.backend.user.model.RoleScreen;

public class RoleScreenMapper {
    public static RoleScreen createEntity(RoleScreenCreateDto createDto) {
        RoleScreen entity = new RoleScreen();
        MapUtil.copyCreateProperties(createDto, entity);
        return entity;
    }

    public static RoleScreenResponseDto createResponse(RoleScreen entity) {
        RoleScreenResponseDto responseDto = new RoleScreenResponseDto();
        MapUtil.copyResponseProperties(entity, responseDto);
        if (entity.getScreen() != null) {
            responseDto.setScreen(ScreenMapper.createResponse(entity.getScreen()));
        }
        return responseDto;
    }
}
