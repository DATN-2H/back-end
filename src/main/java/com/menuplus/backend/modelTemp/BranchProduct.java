package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = { "branch_id", "product_id" }),
  }
)
public class BranchProduct extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer stockQuantity;
  private Integer stockThreshold;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "branchId", nullable = false)
  private Branch branch;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productId", nullable = false)
  private Product product;
}
