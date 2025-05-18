package com.menuplus.backend.library.security;

import java.util.function.Supplier;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

public class PermissionAuthorizationManager
    implements AuthorizationManager<RequestAuthorizationContext> {

    private final PermissionValidator permissionValidator;

    public PermissionAuthorizationManager(
        PermissionValidator permissionValidator
    ) {
        this.permissionValidator = permissionValidator;
    }

    @Override
    public AuthorizationDecision check(
        Supplier<Authentication> authentication,
        RequestAuthorizationContext context
    ) {
        boolean granted = permissionValidator.hasPermission(
            authentication.get(),
            context.getRequest()
        );
        return new AuthorizationDecision(granted);
    }
}
