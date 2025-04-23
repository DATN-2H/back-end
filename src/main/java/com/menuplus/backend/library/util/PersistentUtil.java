package com.menuplus.backend.library.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnitUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersistentUtil {

  @PersistenceContext
  private EntityManager entityManager;

  public boolean isLoaded(Object entity) {
    PersistenceUnitUtil unitUtil = entityManager
      .getEntityManagerFactory()
      .getPersistenceUnitUtil();
    return unitUtil.isLoaded(entity);
  }

  public boolean isLoaded(Object entity, String attributeName) {
    PersistenceUnitUtil unitUtil = entityManager
      .getEntityManagerFactory()
      .getPersistenceUnitUtil();
    return unitUtil.isLoaded(entity, attributeName);
  }

  public void clear() {
    entityManager.clear();
  }

  public void flushAndClear() {
    entityManager.flush();
    entityManager.clear();
  }

  public void detach(Object entity) {
    entityManager.detach(entity);
  }
}
