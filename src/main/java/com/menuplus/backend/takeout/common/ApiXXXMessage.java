package com.menuplus.backend.takeout.common;

import com.menuplus.backend.library.common.ApiMessageBase;
import lombok.Getter;

@Getter
public class ApiXXXMessage extends ApiMessageBase {

    //
    //    // Account: yyy = 1
    //    public static ApiMessageBase
    //            ACCOUNT_NOT_FOUND = new ApiUserMessage(3001001, "account.not_found"),
    //            ACCOUNT_EXISTED = new ApiUserMessage(3001002, "account.existed"),
    //            ACCOUNT_IS_INACTIVE = new ApiUserMessage(3001007, "account.status.disabled"),
    //            ACCOUNT_PASSWORD_MISMATCH = new ApiUserMessage(3001008, "account.password.incorrect"),
    //            PASSWORD_MISMATCH = new ApiUserMessage(3001008, "password.mismatch");
    //
    //    public ApiUserMessage(int code, String message) {
    //        super(code, message);
    //    }

    public ApiXXXMessage(int code, String message) {
        super(code, message);
    }
}
