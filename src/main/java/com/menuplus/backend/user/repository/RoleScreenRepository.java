package com.menuplus.backend.user.repository;

import com.menuplus.backend.user.model.RoleScreen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleScreenRepository extends JpaRepository<RoleScreen, Long> {
  List<RoleScreen> findByRoleId(Long roleId);

  @Modifying
  void deleteByRoleId(Long roleId);
}
