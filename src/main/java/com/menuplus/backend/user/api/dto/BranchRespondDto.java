package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchRespondDto extends BaseResponseDto {

    private Long id;
    private String name;
    private String address;
    private String phone;

    private UserDtoResponse manager;
}
