package com.menuplus.backend.user.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
