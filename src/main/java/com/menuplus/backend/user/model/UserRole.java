package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserRole extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "roleId",
        referencedColumnName = "id",
        insertable = false,
        updatable = false
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;
}
