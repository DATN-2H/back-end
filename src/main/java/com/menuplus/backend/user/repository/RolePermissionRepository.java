package com.menuplus.backend.user.repository;

import com.menuplus.backend.user.model.RolePermission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository
    extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleId(Long roleId);

    @Modifying
    void deleteByRoleId(Long roleId);
}
