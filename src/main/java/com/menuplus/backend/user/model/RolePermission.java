package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RolePermission extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long roleId;
  private Long permissionId;
  private Boolean isLimitedByOwner;
  private String limitedIp;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
    name = "permissionId",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Permission permission;
}
