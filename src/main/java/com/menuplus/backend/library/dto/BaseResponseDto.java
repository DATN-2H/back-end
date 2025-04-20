package com.menuplus.backend.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseResponseDto {
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private GeneralStatus status;

    private String createdUsername;
    private String updatedUsername;

}
