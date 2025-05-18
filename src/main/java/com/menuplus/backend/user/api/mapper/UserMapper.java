package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.MapperHelper;
import com.menuplus.backend.user.api.dto.UserCreateDto;
import com.menuplus.backend.user.api.dto.UserDtoResponse;
import com.menuplus.backend.user.model.User;
import com.menuplus.backend.user.model.User_;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<String> ListExcludeFields = List.of();
    public static List<String> AllExcludeFields = List.of(User_.USER_ROLES);
    public static List<String> PermissionExcludeFields = List.of(
        User_.USER_ROLES
    );

    public static User createUserFromRegisterRequest(UserCreateDto request) {
        User user = new User();
        MapUtil.copyCreateProperties(request, user);
        if (user.getStatus() == null) {
            user.setStatus(GeneralStatus.ACTIVE);
        }

        return user;
    }

    public static UserDtoResponse createResponse(User user) {
        return createResponse(user, List.of());
    }

    public static UserDtoResponse createResponse(
        User entity,
        List<String> excludeFields
    ) {
        UserDtoResponse response = new UserDtoResponse();
        MapUtil.copyResponseProperties(entity, response);

        if (
            !excludeFields.contains(User_.USER_ROLES) &&
            entity.getUserRoles() != null
        ) {
            response.setUserRoles(
                MapperHelper.map(
                    new ArrayList<>(entity.getUserRoles()),
                    UserRoleMapper::createResponse
                )
            );
        }

        if (
            !excludeFields.contains(User_.BRANCH) && entity.getBranch() != null
        ) {
            response.setBranch(
                BranchMapper.createResponse(
                    entity.getBranch()
                    //                    List.of(Branch_.MANAGER)
                )
            );
        }

        return response;
    }
}
