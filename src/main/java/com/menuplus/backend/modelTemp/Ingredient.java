package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Ingredient extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double calories;
  private String description;
  private String quantity; // Ví dụ: "100g", "2 muỗng", "1 lát"

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productId", nullable = false)
  private Product product;
}
