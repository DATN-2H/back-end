package com.menuplus.backend.library.util;

import lombok.Setter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnitUtil;

public class PersistentUtil {

    @Setter
    private static EntityManager entityManager;

    private PersistentUtil() {
    }

    public static boolean isLoaded(Object entity) {
        PersistenceUnitUtil unitUtil = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
        return unitUtil.isLoaded(entity);
    }

    public static boolean isLoaded(Object entity, String attributeName) {
        PersistenceUnitUtil unitUtil = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
        return unitUtil.isLoaded(entity, attributeName);
    }

    public static void clear() {
        entityManager.clear();
    }

    public static void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    public static void detach(Object entity) {
        entityManager.detach(entity);
    }

}
