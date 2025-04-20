package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class UserUpdatePasswordDto {
    private String password;
    private Boolean isSent;
}