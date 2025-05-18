package com.menuplus.backend.user.controller;

import com.menuplus.backend.library.common.Constant;
import com.menuplus.backend.library.common.Response;
import com.menuplus.backend.library.security.UserClaims;
import com.menuplus.backend.user.api.dto.*;
import com.menuplus.backend.user.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCurrentController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/sign-in", consumes = Constant.API_CONTENT_TYPE)
    public Response<SignInResponse> signIn(
        @Valid @RequestBody SignInRequest request
    ) {
        return Response.success(userService.signIn(request));
    }

    @PostMapping(value = "/sign-out")
    public Response<SignOutResponse> signOut() {
        return Response.success(userService.signOut());
    }

    @PostMapping(value = "/verify-token", consumes = Constant.API_CONTENT_TYPE)
    public Response<SignInResponse> verifyToken(
        @Valid @RequestBody VerifyTokenRequest request
    ) {
        return Response.success(userService.verifyToken(request));
    }

    @PostMapping(
        value = "/forget-password",
        consumes = Constant.API_CONTENT_TYPE
    )
    public Response<Void> forgetPassword(
        @Valid @RequestBody UserForgetPasswordRequest request
    ) {
        userService.forgetPassword(request);
        return Response.success();
    }

    @PutMapping(
        value = "/change-password",
        consumes = Constant.API_CONTENT_TYPE
    )
    public Response<Void> changePassword(
        @Valid @RequestBody UserChangePasswordRequest request
    ) {
        userService.changePassword(request);
        return Response.success();
    }
}
