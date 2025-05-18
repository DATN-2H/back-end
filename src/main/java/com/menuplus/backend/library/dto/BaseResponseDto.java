package com.menuplus.backend.library.dto;

import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.user.api.dto.UserDtoResponse;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BaseResponseDto {

    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private GeneralStatus status;

    private String createdUsername;
    private String updatedUsername;

    public void setCreatedUser(String createdUser) {
        if (createdUser == null) {
            createdUsername = "System";
        } else {
            createdUsername = createdUser;
        }
    }

    public void setUpdatedUser(String updatedUser) {
        if (updatedUser != null) {
            updatedUsername = updatedUser;
        }
    }
}
