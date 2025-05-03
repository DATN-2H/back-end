package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Branch extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

  @Column(unique = true)
  private String address;

  @Column(length = 15, unique = true)
  private String phone;
}
