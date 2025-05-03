package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.PaymentFor;
import com.menuplus.backend.library.enumeration.PaymentMethod;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Payment extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private PaymentFor paymentFor;

  @Column(nullable = false)
  private LocalDateTime paymentDate;

  private BigDecimal amount;
  private BigDecimal tipAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookingTableId")
  private BookingTable bookingTable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookingProductId")
  private BookingProduct bookingProduct;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "invoiceId")
  private Invoice invoice;
}
