package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleScreenResponseDto extends BaseResponseDto {

    private Long id;
    private Long roleId;
    private Long screenId;

    private ScreenResponseDto screen;
}
