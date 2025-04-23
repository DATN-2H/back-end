package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RoleScreen extends EntityBase {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "role_screen_gen"
  )
  @SequenceGenerator(
    name = "role_screen_gen",
    sequenceName = "role_screen_seq",
    allocationSize = 1
  )
  private Long id;

  private Long roleId;
  private Long screenId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
    name = "screenId",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Screen screen;
}
