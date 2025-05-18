package com.menuplus.backend.library.security;

import static com.menuplus.backend.library.security.RequestMappingHandlerMappingCustom.clearCurrentRequestMappingInfo;
import static com.menuplus.backend.library.security.RequestMappingHandlerMappingCustom.getCurrentRequestMappingInfo;

import com.menuplus.backend.library.util.BooleanUtil;
import com.menuplus.backend.user.api.dto.PermissionEvaluateDto;
import com.menuplus.backend.user.api.dto.UserDtoResponse;
import com.menuplus.backend.user.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

@Component
@Slf4j
public class PermissionValidator {

    @Autowired
    @Qualifier("requestMappingHandlerMappingCustom")
    private RequestMappingHandlerMappingCustom handlerMapping;

    @Autowired
    private UserService userService;

    public boolean hasPermission(
        Authentication authentication,
        HttpServletRequest request
    ) {
        //    if (request.getMethod() == HttpMethod.OPTIONS.name()) return true;

        UserDetailsCustom userCustom = UserDetailsCustom.getCurrentUser();
        if (userCustom == null) {
            log.error(
                "PermissionValidator: no user found for request {} {}",
                request.getServletPath(),
                request.getMethod()
            );
            return false;
        }

        if (hasFullRole()) return true;

        List<PermissionEvaluateDto> permissionEvaluateDtos =
            userService.getPermissionEvaluate(userCustom.getUserId());

        RequestMappingInfo requestMappingInfo = getMappingInfo(request);
        if (requestMappingInfo == null) return false;

        RequestMethod method = requestMappingInfo
            .getMethodsCondition()
            .getMethods()
            .stream()
            .findFirst()
            .orElse(null);

        String pattern = null;
        if (requestMappingInfo.getPatternsCondition() != null) {
            pattern = requestMappingInfo
                .getPatternsCondition()
                .getPatterns()
                .stream()
                .findFirst()
                .orElse(null);
        } else if (requestMappingInfo.getPathPatternsCondition() != null) {
            pattern = requestMappingInfo
                .getPathPatternsCondition()
                .getPatternValues()
                .stream()
                .findFirst()
                .orElse(null);
        }

        if (method == null || pattern == null) {
            log.warn(
                "Could not determine method or pattern from requestMappingInfo"
            );
            return false;
        }

        final RequestMethod finalMethod = method;
        final String finalPattern = pattern;

        boolean matched = permissionEvaluateDtos
            .stream()
            .anyMatch(
                pv ->
                    pv.getMethod() == finalMethod &&
                    pv.getPattern().equals(finalPattern)
            );

        if (!matched) {
            log.error(
                "PermissionValidator: no matching permission for {} {}",
                request.getServletPath(),
                method
            );
            return false;
        }

        return true;
    }

    public boolean hasFullRole() {
        UserDetailsCustom userCustom = UserDetailsCustom.getCurrentUser();
        UserDtoResponse user = userService.detail(userCustom.getUserId());
        return BooleanUtil.isTrue(user.getIsFullRole());
    }

    private RequestMappingInfo getMappingInfo() {
        RequestAttributes requestAttributes =
            RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return getMappingInfo(request);
    }

    private RequestMappingInfo getMappingInfo(HttpServletRequest request) {
        try {
            // To spring handleMatch or noMatch -> set currentRequestMappingInfo to ThreadLocal
            handlerMapping.getHandler(request);
            return getCurrentRequestMappingInfo();
        } catch (Exception exception) {
            log.error("Failed to get mapping info", exception);
            clearCurrentRequestMappingInfo();
            return null;
        }
    }
}
