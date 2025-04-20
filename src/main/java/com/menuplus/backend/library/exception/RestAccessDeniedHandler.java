package com.menuplus.backend.library.exception;

import com.menuplus.backend.library.common.ApiMessageBase;
import com.menuplus.backend.library.common.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        Response.servletResponse(response, ApiMessageBase.FORBIDDEN);
    }
}