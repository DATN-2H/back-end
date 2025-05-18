package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class PermissionResponseDto {

    private Long id;
    private String code;
    private String name;
    private String permissionGroup;
}
