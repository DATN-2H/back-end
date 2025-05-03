package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ComboProduct extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity = 1; // Số lượng sản phẩm trong combo

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comboId", nullable = false)
  private Combo combo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productId", nullable = false)
  private Product product;
}
