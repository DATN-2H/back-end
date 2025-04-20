package com.menuplus.backend.user.api.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UserRoleCreateDto {
    private Long id;
    private Long userId;
    @NotNull
    private Long roleId;
}
