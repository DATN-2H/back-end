package com.menuplus.backend.user.api.dto;

import lombok.Data;

@Data
public class UserChangePasswordRequest {

    private String password;

    private String newPassword;
}
