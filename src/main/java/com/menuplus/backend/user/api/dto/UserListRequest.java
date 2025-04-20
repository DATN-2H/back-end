package com.menuplus.backend.user.api.dto;

import com.menuplus.backend.library.dto.BaseListRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Data
public class UserListRequest extends BaseListRequest {
    private List<Long> excludeIds;
    private Boolean isFullRole;

    private String email;
    private String username;
    private String fullName;
    private String birthdate;
    private String gender;
    private String phoneNumber;

    private String userRole;
}
