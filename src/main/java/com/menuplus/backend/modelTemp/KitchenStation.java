package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class KitchenStation extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "branchId", nullable = false)
  private Branch branch;
}
