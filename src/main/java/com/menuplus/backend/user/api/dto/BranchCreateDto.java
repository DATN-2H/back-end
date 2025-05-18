package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import lombok.Data;

@Data
public class BranchCreateDto extends BaseResponseDto {

    private String name;
    private String address;
    private String phone;

    private Long managerId;
    private GeneralStatus status;
}
