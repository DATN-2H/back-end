package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class BookingTableTable extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookingTableId", nullable = false)
  private BookingTable bookingTable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tableId", nullable = false)
  private RestaurantTable table;
}
