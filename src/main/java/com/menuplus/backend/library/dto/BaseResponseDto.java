package com.menuplus.backend.library.dto;

import com.menuplus.backend.library.enumeration.GeneralStatus;
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
}
