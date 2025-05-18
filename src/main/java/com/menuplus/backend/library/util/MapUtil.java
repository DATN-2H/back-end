package com.menuplus.backend.library.util;

import com.menuplus.backend.library.dto.BaseResponseDto;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class MapUtil {

    public static void copyNonNullProperties(Object source, Object target) {
        IgnoreNullBeanUtils.getInstance().copyProperties(source, target);
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    public static void copyProperties(
        Object source,
        Object target,
        String... ignoreProperties
    ) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    public static void copyCreateProperties(
        Object source,
        Object target,
        String... ignoreProperties
    ) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
        setDefaultValue(target, "isSystem", Boolean.FALSE);
        setDefaultValue(target, "status", GeneralStatus.ACTIVE);
    }

    public static void copyUpdateProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, "id");
        setDefaultValue(target, "isSystem", Boolean.FALSE);
        setDefaultValue(target, "status", GeneralStatus.ACTIVE);
    }

    public static void copyResponseProperties(
        Object source,
        Object target,
        String... ignoreProperties
    ) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
        setResponseUser(source, target, "createdUsername");
        setResponseUser(source, target, "updatedUsername");
    }

    private static void setResponseUser(
        Object source,
        Object target,
        String fieldName
    ) {
        try {
            Field fieldCreatedUsername = source
                .getClass()
                .getSuperclass()
                .getDeclaredField(fieldName);
            fieldCreatedUsername.setAccessible(true);
            Object createdUsername = fieldCreatedUsername.get(source);
            if (createdUsername != null) {
                Class<?> c = Class.forName(
                    "com.menuplus.backend.user.model.User"
                );
                Method method = c.getDeclaredMethod("getDisplayName");
                String displayName = (String) method.invoke(createdUsername);
                if ("createdUsername".equals(fieldName)) {
                    ((BaseResponseDto) target).setCreatedUsername(displayName);
                } else {
                    ((BaseResponseDto) target).setUpdatedUsername(displayName);
                }
            } else {
                log.debug("createdUsername is null");
            }
        } catch (Exception ignore) {}
    }

    private static void setDefaultValue(
        Object obj,
        String fieldName,
        Object fieldValue
    ) {
        try {
            Field field;
            try {
                field = obj.getClass().getDeclaredField(fieldName);
            } catch (Exception e) {
                field = obj
                    .getClass()
                    .getSuperclass()
                    .getDeclaredField(fieldName);
            }
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value == null) {
                field.set(obj, fieldValue);
            }
        } catch (Exception ignore) {}
    }
}
