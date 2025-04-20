package com.menuplus.backend.user.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleScreenCreateDto {
    private Long id;
    private Long roleId;
    @NotNull
    private Long screenId;
}
