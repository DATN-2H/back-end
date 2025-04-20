package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.ScreenResponseDto;
import com.menuplus.backend.user.model.Screen;

public class ScreenMapper {
    public static ScreenResponseDto createResponse(Screen entity) {
        ScreenResponseDto responseDto = new ScreenResponseDto();
        MapUtil.copyResponseProperties(entity, responseDto);
        return responseDto;
    }
}

