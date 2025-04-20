package com.menuplus.backend.user.repository;

import com.menuplus.backend.user.model.User;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByEmailAndStatusIsNot(String email, GeneralStatus status);

    User findFirstByIsFullRoleOrderByIdAsc(Boolean isFullRole);

    @Query("select u.id from User u")
    List<Long> findAllIds();

    List<User> findByUserRoles_RoleId(Long roleId);

}
