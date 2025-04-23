package com.menuplus.backend.user.common;

import com.menuplus.backend.library.common.ApiMessageBase;
import lombok.Getter;

@Getter
public class ApiUserMessage extends ApiMessageBase {

  // Account: yyy = 1
  public static ApiMessageBase ACCOUNT_NOT_FOUND = new ApiUserMessage(
    3001001,
    "account.not_found"
  ), ACCOUNT_EXISTED = new ApiUserMessage(
    3001002,
    "account.existed"
  ), ACCOUNT_IS_INACTIVE = new ApiUserMessage(
    3001007,
    "account.status.disabled"
  ), ACCOUNT_PASSWORD_MISMATCH = new ApiUserMessage(
    3001008,
    "account.password.incorrect"
  ), PASSWORD_MISMATCH = new ApiUserMessage(
    3001008,
    "password.mismatch"
  ), USER_NOT_FOUND = new ApiUserMessage(
    3002001,
    "user.not_found"
  ), ROLE_CREATE_SUCCESS = new ApiUserMessage(
    3002001,
    "role.create_success"
  ), ROLE_UPDATE_SUCCESS = new ApiUserMessage(
    3002001,
    "role.update_success"
  ), ROLE_DELETE_SUCCESS = new ApiUserMessage(
    3002001,
    "role.delete_success"
  ), ROLE_EXPORT_SUCCESS = new ApiUserMessage(
    3002001,
    "role.export_success"
  ), ROLE_NOT_FOUND = new ApiUserMessage(3002001, "role.not_found");

  public ApiUserMessage(int code, String message) {
    super(code, message);
  }
}
