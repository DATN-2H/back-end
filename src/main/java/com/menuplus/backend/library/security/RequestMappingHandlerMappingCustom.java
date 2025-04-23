package com.menuplus.backend.library.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class RequestMappingHandlerMappingCustom
  extends RequestMappingHandlerMapping {

  private static final ThreadLocal<
    RequestMappingInfo
  > currentRequestMappingInfo = new ThreadLocal<>();

  public static void setCurrentRequestMappingInfo(
    RequestMappingInfo requestMappingInfo
  ) {
    currentRequestMappingInfo.set(requestMappingInfo);
  }

  public static RequestMappingInfo getCurrentRequestMappingInfo() {
    return currentRequestMappingInfo.get();
  }

  public static void clearCurrentRequestMappingInfo() {
    currentRequestMappingInfo.remove();
  }

  @Override
  protected void handleMatch(
    RequestMappingInfo info,
    String lookupPath,
    HttpServletRequest request
  ) {
    super.handleMatch(info, lookupPath, request);
    setCurrentRequestMappingInfo(info);
  }

  @Override
  protected HandlerMethod handleNoMatch(
    Set<RequestMappingInfo> infos,
    String lookupPath,
    HttpServletRequest request
  ) throws ServletException {
    clearCurrentRequestMappingInfo();
    return super.handleNoMatch(infos, lookupPath, request);
  }
}
