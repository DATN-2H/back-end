package com.menuplus.backend.user.api.service;

import com.menuplus.backend.library.security.UserClaims;
import com.menuplus.backend.user.api.dto.PermissionEvaluateDto;
import com.menuplus.backend.user.api.dto.*;

import java.util.List;

public interface UserService {

    UserDtoResponse create(UserCreateDto dtoCreate);

    SignInResponse signIn(SignInRequest request);

    SignOutResponse signOut();

    UserClaims verifyToken(VerifyTokenRequest request);

    void forgetPassword(UserForgetPasswordRequest request);

    void changePassword(UserChangePasswordRequest request);

    List<PermissionEvaluateDto> getPermissionEvaluate(Long userId);

    UserDtoResponse update(Long id, UserUpdateDto updateDto);

    UserDtoResponse detail(Long id);

    void delete(Long id);

}
