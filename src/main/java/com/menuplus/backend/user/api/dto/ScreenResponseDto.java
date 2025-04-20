package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class ScreenResponseDto {
    private Long id;
    private String code;
    private String name;
    private String menuGroup;
    private String menuItem;
}