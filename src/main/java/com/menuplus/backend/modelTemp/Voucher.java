package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Voucher extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  private Integer totalIssued;
  private Integer totalUsed;
  private Integer maxUsagePerUser;
  private BigDecimal discountValue;
  private Double discountPercent;
  private String condition;
}
