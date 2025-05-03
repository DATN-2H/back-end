package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ChefKitchenStation extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kitchenStationId", nullable = false)
  private KitchenStation kitchenStation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chefId", nullable = false)
  private User chef;
}
