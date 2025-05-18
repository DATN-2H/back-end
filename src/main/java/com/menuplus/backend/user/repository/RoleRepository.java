package com.menuplus.backend.user.repository;

import com.menuplus.backend.library.enumeration.SystemRole;
import com.menuplus.backend.user.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Role findByName(SystemRole name);
}
