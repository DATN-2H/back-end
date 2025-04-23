package com.menuplus.backend.library.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.menuplus.backend.library.common.ApiMessageBase;
import com.menuplus.backend.library.common.Response;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class RestExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(
    RestExceptionHandler.class
  );

  private void writeLog(ApiMessageBase apiMessage, Exception exception) {
    writeLog(apiMessage.getCode(), apiMessage.getMessage(), exception);
  }

  private void writeLog(int code, String message, Exception exception) {
    String logFormat = "%s: %s. Exception: %s ---- %s";
    logger.error(
      String.format(
        logFormat,
        code,
        message,
        exception.getMessage(),
        Arrays.toString(exception.getStackTrace())
      )
    );
  }

  private void writeLog(Exception exception) {
    String logFormat = "%s ---- %s";
    logger.error(
      String.format(
        logFormat,
        exception.getMessage(),
        Arrays.toString(exception.getStackTrace())
      )
    );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public void handleUsernameNotFoundException(
    Exception exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.UNAUTHORIZED, exception);
    Response.servletResponse(response, ApiMessageBase.UNAUTHORIZED);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(
    AccessDeniedException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.FORBIDDEN, exception);
    Response.servletResponse(response, ApiMessageBase.FORBIDDEN);
  }

  /**
   * handle media type are not supported, currently only support for application/json
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public void handleHttpMediaTypeException(
    Exception exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.UNSUPPORTED_MEDIA_TYPE, exception);
    Response.servletResponse(response, ApiMessageBase.UNSUPPORTED_MEDIA_TYPE);
  }

  /**
   * handle validation request exception: null {@link NotNull}, empty {@link NotEmpty}, ...
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void handleMethodArgumentNotValidException(
    MethodArgumentNotValidException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.INVALID_PARAM, exception);

    List<FieldError> fieldErrorList = exception
      .getBindingResult()
      .getFieldErrors();

    if (!fieldErrorList.isEmpty()) {
      for (FieldError fieldError : fieldErrorList) {
        String message = fieldError.getDefaultMessage();
        Response.servletResponse(
          response,
          ApiMessageBase.INVALID_PARAM.getCode(),
          message
        );
        return;
      }
    }
    Response.servletResponse(response, ApiMessageBase.INVALID_PARAM);
  }

  /**
   * handle invalid format exception. Ex: invalid enum value, ...
   *
   * @param response
   * @param ex
   * @throws IOException
   */
  @ExceptionHandler(InvalidFormatException.class)
  public void handleInvalidFormatException(
    HttpServletResponse response,
    InvalidFormatException ex
  ) throws IOException {
    writeLog(ApiMessageBase.NOT_READABLE_PARAM, ex);

    if (ex.getPath() != null && !ex.getPath().isEmpty()) {
      String message =
        ex.getPath().get(0).getFieldName() +
        " \"" +
        ex.getValue() +
        "\" is invalid";
      Response.servletResponse(
        response,
        ApiMessageBase.NOT_READABLE_PARAM.getCode(),
        message
      );
    } else {
      Response.servletResponse(response, ApiMessageBase.NOT_READABLE_PARAM);
    }
  }

  /**
   * handle message not readable, include {@link InvalidFormatException}
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public void handleHttpMessageNotReadableException(
    HttpMessageNotReadableException exception,
    HttpServletResponse response
  ) throws IOException {
    if (exception.getCause() instanceof InvalidFormatException) {
      handleInvalidFormatException(
        response,
        (InvalidFormatException) exception.getCause()
      );
    } else {
      writeLog(ApiMessageBase.NOT_READABLE_PARAM, exception);
      Response.servletResponse(response, ApiMessageBase.NOT_READABLE_PARAM);
    }
  }

  /**
   * handle api exception (normally throw from service layer): invalid business logic, ...
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(ApiException.class)
  public void handleApiException(
    ApiException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(exception);
    Response.servletResponse(
      response,
      exception.getCode(),
      exception.getMessage()
    );
  }

  /**
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(RestTemplateException.class)
  public void handleRestTemplateException(
    RestTemplateException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(exception);
    Response.servletResponse(
      response,
      exception.getCode(),
      exception.getMessage()
    );
  }

  /**
   * handle missing params
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public void handleMissingParam(
    MissingServletRequestParameterException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.MISSING_PARAM, exception);
    String message = exception.getParameterName();
    Response.servletResponse(
      response,
      ApiMessageBase.MISSING_PARAM.getCode(),
      message + " param is missing"
    );
  }

  /**
   * handle if upload size exceeded
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public void handleUploadSizeExceeded(
    MaxUploadSizeExceededException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.UPLOAD_SIZE_EXCEEDED, exception);
    Response.servletResponse(response, ApiMessageBase.UPLOAD_SIZE_EXCEEDED);
  }

  /**
   * handle if type mismatch
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public void handleTypeMismatchException(
    MethodArgumentTypeMismatchException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.INVALID_PARAM, exception);
    String message =
      exception.getName() + " \"" + exception.getValue() + "\" is invalid";
    Response.servletResponse(
      response,
      ApiMessageBase.INVALID_PARAM.getCode(),
      message
    );
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public void handleTypeMismatchException(
    HttpRequestMethodNotSupportedException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.REQUEST_METHOD_NOT_SUPPORT, exception);
    String message = exception.getMessage();
    Response.servletResponse(
      response,
      ApiMessageBase.REQUEST_METHOD_NOT_SUPPORT.getCode(),
      message
    );
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public void handleDataIntegrityViolationException(
    DataIntegrityViolationException exception,
    HttpServletResponse response
  ) throws IOException {
    if (exception.getCause() instanceof ConstraintViolationException) {
      handleConstraintViolationException(
        (ConstraintViolationException) exception.getCause(),
        response
      );
    } else if (exception.getCause() instanceof DataException) {
      handleDataException((DataException) exception.getCause(), response);
    } else {
      writeLog(ApiMessageBase.DATA_INTEGRITY_VIOLATION, exception);
      Response.servletResponse(
        response,
        ApiMessageBase.DATA_INTEGRITY_VIOLATION.getCode(),
        exception.getMessage()
      );
    }
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public void handleConstraintViolationException(
    ConstraintViolationException exception,
    HttpServletResponse response
  ) throws IOException {
    var detailMessage = exception.getSQLException().getMessage();
    var code = StringUtils.substring(
      detailMessage,
      detailMessage.indexOf("=(") + 2,
      detailMessage.indexOf("already exists") - 2
    ); // DETACH CODE UNIQUE

    writeLog(ApiMessageBase.DATA_INTEGRITY_VIOLATION, exception);
    Response.servletResponse(
      response,
      ApiMessageBase.DATA_INTEGRITY_VIOLATION.getCode(),
      ApiMessageBase.DATA_INTEGRITY_VIOLATION + exception.getConstraintName()
    );
  }

  @ExceptionHandler(DataException.class)
  public void handleDataException(
    DataException exception,
    HttpServletResponse response
  ) throws IOException {
    if (exception.getCause() instanceof PSQLException) {
      handlePSQLException((PSQLException) exception.getCause(), response);
    } else {
      writeLog(ApiMessageBase.DATA_INTEGRITY_VIOLATION, exception);
      Response.servletResponse(
        response,
        ApiMessageBase.DATA_INTEGRITY_VIOLATION.getCode(),
        exception.getMessage()
      );
    }
  }

  @ExceptionHandler(PSQLException.class)
  public void handlePSQLException(
    PSQLException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.DATA_INTEGRITY_VIOLATION, exception);
    Response.servletResponse(
      response,
      ApiMessageBase.DATA_INTEGRITY_VIOLATION.getCode(),
      STR."data_integrity_violate.\{exception.getSQLState()}"
    );
  }

  @ExceptionHandler(JwtException.class)
  public void handleJwtException(
    JwtException exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.UNAUTHORIZED, exception);
    Response.servletResponse(response, ApiMessageBase.UNAUTHORIZED);
  }

  /*
   * add more ExceptionHandler if necessary
   */

  /**
   * handle all other exception
   *
   * @param exception
   * @param response
   * @throws IOException
   */
  @ExceptionHandler(Exception.class)
  public void handleUnknownException(
    Exception exception,
    HttpServletResponse response
  ) throws IOException {
    writeLog(ApiMessageBase.INTERNAL_SERVER_ERROR, exception);
    Response.servletResponse(response, ApiMessageBase.INTERNAL_SERVER_ERROR);
  }
}
