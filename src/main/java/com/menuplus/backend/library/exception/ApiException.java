package com.menuplus.backend.library.exception;

import com.menuplus.backend.library.common.ApiMessageBase;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  private final int code;
  private final String message;

  public ApiException(ApiMessageBase apiMessage) {
    super(apiMessage.getMessage());
    this.code = apiMessage.getCode();
    this.message = apiMessage.getMessage();
  }

  public ApiException(ApiMessageBase apiMessage, Object[] translatorObjects) {
    super(apiMessage.getMessage());
    this.code = apiMessage.getCode();
    this.message = apiMessage.getMessage();
  }

  public ApiException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public ApiException(int code, String message, boolean needTranslate) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public ApiException(
    int code,
    String message,
    boolean needTranslate,
    Object[] translatorObjects
  ) {
    super(message);
    this.code = code;
    this.message = message;
  }
}
