package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Promotion extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private LocalDateTime startDatetime;

  @Column(nullable = false)
  private LocalDateTime endDatetime;

  private BigDecimal discountValue; // Giá trị giảm giá
  private Double percent; // Phần trăm giảm giá

  @OneToMany(
    mappedBy = "promotion",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PromotionProduct> products = new ArrayList<>();
}
