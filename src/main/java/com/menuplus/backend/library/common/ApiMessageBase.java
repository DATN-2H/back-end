package com.menuplus.backend.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Format: xxxyyyzzz
 * <p>
 * xxx - Module code:
 * Common = 1,
 * Account = 2,
 * <p>
 * yyy - Entity Code:
 * Example: user = 1,
 * <p>
 * zzz - Error Code:
 * Example: not_found = 1
 */
@AllArgsConstructor
@Getter
public class ApiMessageBase {

  public static ApiMessageBase SUCCESS = new ApiMessageBase(
    0,
    "success"
  ), UNAUTHORIZED = new ApiMessageBase(401, "unauthorized"), FORBIDDEN =
    new ApiMessageBase(403, "forbidden"), NOT_FOUND = new ApiMessageBase(
    404,
    "not-found"
  ), UNSUPPORTED_MEDIA_TYPE = new ApiMessageBase(
    415,
    "unsupported-media-type"
  ), INVALID_PARAM = new ApiMessageBase(
    499,
    "invalid-params"
  ), NOT_READABLE_PARAM = new ApiMessageBase(
    498,
    "not-readable-params"
  ), MISSING_PARAM = new ApiMessageBase(
    497,
    "missing-params"
  ), UPLOAD_SIZE_EXCEEDED = new ApiMessageBase(
    496,
    "upload-size-exceeded"
  ), REQUEST_METHOD_NOT_SUPPORT = new ApiMessageBase(
    405,
    "request-method-not-supported"
  ), DATA_INTEGRITY_VIOLATION = new ApiMessageBase(
    495,
    "data-integrity-violation"
  ), INTERNAL_SERVER_ERROR = new ApiMessageBase(
    500,
    "internal-server-error"
  ), COLUMN_NOT_FOUND = new ApiMessageBase(3002000, "column.not_found");

  protected final int code;
  protected final String message;
}
