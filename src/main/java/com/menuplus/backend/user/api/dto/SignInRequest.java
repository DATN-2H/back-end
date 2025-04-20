package com.menuplus.backend.user.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class SignInRequest {

    @NotNull(message = "{account.email.null}")
    private String email;

    @NotNull(message = "{account.login.password.null}")
    private String password;

    public void setEmail(String email) {
        this.email = email != null ? email.toLowerCase() : null;
    }
}
