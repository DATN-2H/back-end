package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OrderCombo extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;
  private String note;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orderId", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comboId", nullable = false)
  private Combo combo;
}
