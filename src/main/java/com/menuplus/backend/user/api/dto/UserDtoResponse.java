package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseResponseDto;
import com.menuplus.backend.library.enumeration.Gender;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDtoResponse extends BaseResponseDto {

  private Long id;
  private String email;
  private String username;
  private String fullName;
  private LocalDate birthdate;
  private Gender gender;
  private String phoneNumber;
  private Boolean isFullRole;

  private List<UserRoleResponseDto> userRoles;
}
