package com.menuplus.backend.user.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.menuplus.backend.library.enumeration.Gender;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserCreateDto {
    @NotNull(message = "{account.email.null}")
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Gender gender;
    private String phoneNumber;
    private Boolean isFullRole;
    private String password;

    private List<@Valid UserRoleCreateDto> userRoles;

    public void setUsername(String username) {
        this.username = username != null ? username.toLowerCase() : null;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.toLowerCase() : null;
    }
}