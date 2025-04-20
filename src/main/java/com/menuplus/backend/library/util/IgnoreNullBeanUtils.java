package com.menuplus.backend.library.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;

@Slf4j
public class IgnoreNullBeanUtils extends BeanUtilsBean {

    private static final IgnoreNullBeanUtils INSTANCE = new IgnoreNullBeanUtils();

    private IgnoreNullBeanUtils() {

    }

    public static IgnoreNullBeanUtils getInstance() {
        return INSTANCE;
    }

    @Override
    public void copyProperties(Object source, Object target) {
        try {
            super.copyProperties(target, source);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void copyProperty(Object bean, String name, Object value) {
        try {
            if (value == null) {
                return;
            }
            super.copyProperty(bean, name, value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}