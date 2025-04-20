package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseListRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Data
public class RoleListRequest extends BaseListRequest {
    private String name;
    private String description;
}