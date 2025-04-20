package com.menuplus.backend.user.api.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
public class PermissionEvaluateDto {
    private Long id;
    private String code;
    private String name;
    private RequestMethod method;
    private String pattern;
}
