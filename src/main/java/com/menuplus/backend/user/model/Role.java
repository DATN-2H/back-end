package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.SystemRole;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SystemRole name;

    @Column(unique = true)
    private String hexColor;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<RolePermission> rolePermissions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<RoleScreen> roleScreens = new HashSet<>();
}
