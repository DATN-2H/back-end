package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OrderProduct extends EntityBase {

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
  @JoinColumn(name = "productId", nullable = false)
  private Product product;
}
